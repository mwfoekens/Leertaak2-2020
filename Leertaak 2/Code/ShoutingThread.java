package Code;

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

            PrintWriter printWriter = new PrintWriter(connection.getOutputStream(), true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            while ((s = bufferedReader.readLine()) != null) {
//                printWriter.println(s.toUpperCase());
                System.out.println(s);
            }

            // now close the socket connection
            connection.close();
            System.err.println("Connection closed: workerthread ending");
            // upping the semaphore.. since the connnection is gone....
            ShoutingMTServer.mijnSemafoor.verhoog();
        } catch (IOException | InterruptedException ioe) {
        }
    }
}

