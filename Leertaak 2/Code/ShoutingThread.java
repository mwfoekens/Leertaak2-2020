package Code;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.net.*;
import java.io.*;
//import java.util.concurrent.*;

class Worker implements Runnable {
    private Socket connection;

    public Worker(Socket connection) {
        this.connection = connection;
    }

    public void run() {
        try {
            String s;
            System.err.println("New worker thread started");

            //lets check if we already accepted maximum number of connections
            ShoutingMTServer.mijnSemafoor.probeer();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            JAXBContext jaxbContext = JAXBContext.newInstance(WeatherData.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            StringBuilder builder = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<?xml")) {
                    if (builder != null) {
                        WeatherData q = (WeatherData) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(builder.toString().getBytes()));
                        System.out.println(q); // TODO zet op queue + data correctie
                    }
                    builder = new StringBuilder();
                }
                builder.append(line);
            }
            WeatherData q = (WeatherData) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(builder.toString().getBytes()));

            System.out.println(q); // TODO zet op queue + data correctie


            // now close the socket connection
            connection.close();
            System.err.println("Connection closed: workerthread ending");
            // upping the semaphore.. since the connnection is gone....
            ShoutingMTServer.mijnSemafoor.verhoog();
        } catch (IOException | InterruptedException | JAXBException ioe) {
        }
    }
}

