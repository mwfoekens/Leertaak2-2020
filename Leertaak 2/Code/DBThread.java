package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBThread implements Runnable {
    Queue queue = new Queue();
    Connection c;
    private final String url = "jdbc:postgresql://localhost:54061/undwmi";
    private final String user = "postgres";
    private final String password = "hoi";

    public DBThread() {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("test");
            c = DriverManager.getConnection(url, user, password);
            System.out.println("123");
            System.out.print(c);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    @Override
    public void run() {

    }
}

