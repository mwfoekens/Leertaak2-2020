package Code;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoutingMTServer {
    public static final int PORT = 2500;
    private static final int maxnrofConnections = 1;
    public static TelSemafoor mijnSemafoor = new TelSemafoor(maxnrofConnections);


    public static void main(String[] args) throws InterruptedException, JAXBException, IOException {
        Socket connection;
//        Thread dbThread = new Thread(new DBThread());
//        dbThread.start();
        ServerSocket server = new ServerSocket(PORT);
        System.err.println("MT Server started.. bring on the load, to a maximum of: " + maxnrofConnections);

        while (true) {
            connection = server.accept();
            System.err.println("New connection accepted..handing it over to worker thread");
            Thread worker = new Thread(new Worker(connection));
            worker.start();
        }
    }
}
