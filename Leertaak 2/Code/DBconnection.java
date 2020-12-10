package Code;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBconnection {
    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost/weather";
        Properties props = new Properties();
        props.setProperty("user","postgres");
        props.setProperty("password","panda123");
        props.setProperty("ssl","false");

        try {
            Connection conn = DriverManager.getConnection(url, props);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM stations");
            while (resultSet.next()) {
                System.out.print( resultSet.getString("name"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
