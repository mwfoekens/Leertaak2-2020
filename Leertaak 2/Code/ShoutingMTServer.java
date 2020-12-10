package Code;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ShoutingMTServer {
    public static final int PORT = 2500;
    private static final int maxnrofConnections = 1;
    public static TelSemafoor mijnSemafoor = new TelSemafoor(maxnrofConnections);


    public static void main(String[] args) throws InterruptedException, JAXBException {
        String measurement = "<?xml version=\"1.0\"?>\n" +
                "<WEATHERDATA>\n" +
                "<MEASUREMENT>\n" +
                "    <STN>123456</STN>\n" +
                "    <DATE>2009-09-13</DATE>\n" +
                "    <TIME>15:59:46</TIME>\n" +
                "    <TEMP>-60.1</TEMP>\n" +
                "    <DEWP>-58.1</DEWP>\n" +
                "    <STP>1034.5</STP>\n" +
                "    <SLP>1007.6</SLP>\n" +
                "    <VISIB>123.7</VISIB>\n" +
                "    <WDSP>10.8</WDSP>\n" +
                "    <PRCP>11.28</PRCP>\n" +
                "    <SNDP>11.1</SNDP>\n" +
                "    <FRSHTT>010101</FRSHTT>\n" +
                "    <CLDC>87.4</CLDC>\n" +
                "    <WNDDIR>342</WNDDIR>\n" +
                "</MEASUREMENT>\n" +
                "<MEASUREMENT>\n" +
                "    <STN>123456</STN>\n" +
                "    <DATE>2009-09-13</DATE>\n" +
                "    <TIME>15:59:46</TIME>\n" +
                "    <TEMP>-60.1</TEMP>\n" +
                "    <DEWP>-58.1</DEWP>\n" +
                "    <STP>1034.5</STP>\n" +
                "    <SLP>1007.6</SLP>\n" +
                "    <VISIB>123.7</VISIB>\n" +
                "    <WDSP>10.8</WDSP>\n" +
                "    <PRCP>11.28</PRCP>\n" +
                "    <SNDP>11.1</SNDP>\n" +
                "    <FRSHTT>010101</FRSHTT>\n" +
                "    <CLDC>87.4</CLDC>\n" +
                "    <WNDDIR>342</WNDDIR>\n" +
                "</MEASUREMENT>\n" +
                "</WEATHERDATA>";
        InputStream targetStream = new ByteArrayInputStream(measurement.getBytes());
        JAXBContext jaxbContext = JAXBContext.newInstance(WeatherData.class);

        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        WeatherData test = (WeatherData) jaxbUnmarshaller.unmarshal(targetStream);
        System.out.println(test);
//        Socket connection;
//        Thread dbThread = new Thread(new DBThread());
//        dbThread.start();
//        TimeUnit.SECONDS.sleep(5);
//            ServerSocket server = new ServerSocket(PORT);
//            System.err.println("MT Server started.. bring on the load, to a maximum of: " + maxnrofConnections);
//
//            while (true) {
//                connection = server.accept();
//                System.err.println("New connection accepted..handing it over to worker thread");
//                Thread worker = new Thread(new Worker(connection));
//                worker.start();
//            }
    }
}
