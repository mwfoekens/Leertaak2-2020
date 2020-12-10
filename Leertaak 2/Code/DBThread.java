package Code;

import java.sql.*;
import java.util.Properties;

public class DBThread implements Runnable {
    private Queue queue = new Queue();
    private Connection c;

    public DBThread() {
        String url = "jdbc:postgresql://localhost/unwdmi";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "hoi");
        props.setProperty("ssl", "false");
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager.getConnection(url, props);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("\nOpened database successfully");
    }

    @Override
    public void run() {
        while (!queue.isEmpty()) {
            String toBeAdded = null;
            toBeAdded = queue.getData();
            // DB shenanigans
        }
    }
}
