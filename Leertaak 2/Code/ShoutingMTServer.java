package Code;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ShoutingMTServer {
    public static final int PORT = 2500;
    private static final int maxnrofConnections = 800;
    public static TelSemafoor mijnSemafoor = new TelSemafoor(maxnrofConnections);

    public static void main(String[] args) throws JAXBException, IOException {
        Socket connection;
        Thread dbThread = new Thread(new DBThread());
        dbThread.start();
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
