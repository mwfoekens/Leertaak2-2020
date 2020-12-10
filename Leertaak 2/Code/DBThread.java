package Code;

import java.sql.*;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class DBThread implements Runnable {
    private final BlockingQueue<String> queue = new LinkedBlockingQueue<>();
    private Connection connection;
    public boolean running = true;

    public DBThread() {
        String url = "jdbc:postgresql://localhost/unwdmi";
        Properties props = new Properties();
        props.setProperty("user", "postgres");
        props.setProperty("password", "hoi");
        props.setProperty("ssl", "false");
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, props);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("\nOpened database successfully");
    }

    @Override
    public void run() {
        try {
            Statement statement = connection.createStatement();
            while (running) {
                String values = queue.take();
                statement.executeUpdate("insert into measurements (stn, \"date\", \"time\", \"temp\", dewp, stp, slp, visib, wdsp, prcp, sndp, frshtt, cldc, wnddir)\n" +
                        "values " + values + ";");
            }
        } catch (SQLException | InterruptedException throwables) {
            throwables.printStackTrace();
        }
    }

}
