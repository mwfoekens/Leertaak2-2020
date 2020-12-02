package Code;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Communication {

    public Communication(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        Socket socket = serverSocket.accept();

        socket.close();
    }
    

}
