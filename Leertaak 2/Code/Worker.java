package Code;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


class Worker implements Runnable {
    private Socket connection;
    private BufferedReader bufferedReader;
    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;

    public Worker(Socket connection) throws JAXBException, IOException {
        this.connection = connection;
        this.bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        this.jaxbContext = JAXBContext.newInstance(WeatherData.class);
        this.jaxbUnmarshaller = jaxbContext.createUnmarshaller();
    }

    @Override
    public void run() {
        try {
            String s;
            System.err.println("New worker thread started");

            //lets check if we already accepted maximum number of connections
            ShoutingMTServer.mijnSemafoor.probeer();

            StringBuilder builder = null;
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.startsWith("<?xml")) {
                    if (builder != null) {
                        WeatherData q = getWeatherData(builder);
                        //System.out.println(q); // TODO zet op queue
                        for (int i = 0; i < q.getMeasurements().size(); i++) {
                            correctIfEmpty(q.getMeasurements().get(i));
                        }
                    }
                    builder = new StringBuilder();
                }
                builder.append(line);
            }
            WeatherData q = getWeatherData(builder);
            for (int i = 0; i < q.getMeasurements().size(); i++) {
                correctIfEmpty(q.getMeasurements().get(i));
            }
            //System.out.println(q); // TODO zet op queue

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed: workerthread ending");
            // upping the semaphore.. since the connnection is gone....
            ShoutingMTServer.mijnSemafoor.verhoog();
        } catch (IOException | InterruptedException | JAXBException ioe) {
            System.err.println("err");
        }
    }

    private WeatherData getWeatherData(StringBuilder builder) throws JAXBException {
        return (WeatherData) jaxbUnmarshaller.unmarshal(new ByteArrayInputStream(builder.toString().getBytes()));
    }

    private void correctIfEmpty(Measurement measurement) {
        if (measurement.getStn().equals("")) {
            measurement.setStn("0");
        }
        if (measurement.getCldc().equals("")) {
            measurement.setCldc("0");
        }
        if (measurement.getWnddir().equals("")) {
            measurement.setWnddir("0");
        }
        if (measurement.getDewp().equals("")) {
            measurement.setDewp("0");
        }
        if (measurement.getFrshtt().equals("")) {
            measurement.setFrshtt("0");
        }
        if (measurement.getPrcp().equals("")) {
            measurement.setPrcp("0");
        }
        if (measurement.getSlp().equals("")) {
            measurement.setSlp("0");
        }
        if (measurement.getSndp().equals("")) {
            measurement.setSndp("0");
        }
        if (measurement.getStp().equals("")) {
            measurement.setStp("0");
        }
        if (measurement.getTemp().equals("")) {
            measurement.setTemp("0");
        }
        if (measurement.getVisib().equals("")) {
            measurement.setVisib("0");
        }
        if (measurement.getWdsp().equals("")) {
            measurement.setWdsp("0");
        }
    }
}

