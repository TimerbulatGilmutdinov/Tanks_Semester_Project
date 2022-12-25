
import exceptions.IllegalProtocolMethodException;
import exceptions.IllegalProtocolNameException;
import parsers.HeaderParser;
import parsers.HeaderValueParser;
import parsers.RequestInfoParser;
import receivers.RequestReceiver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    static List<String> argsList = new ArrayList<>();
    static Socket client;
    static void setClient() throws IOException{
        client = new Socket("127.0.0.1",8080);
    }
    public static void main(String[] args) throws IOException, IllegalProtocolMethodException, IllegalProtocolNameException {
//        ServerSocket serverSocket = new ServerSocket(8080);
//        serverSocket.accept();
//        setClient();
//
//        BufferedOutputStream outputStream = new BufferedOutputStream(client.getOutputStream());
//        outputStream.write("TANK_COORD_X:14".getBytes());
//        outputStream.write("TANK_COORD_Y:11".getBytes());
//        outputStream.write("TURRET_ANGLE:14".getBytes());
        InputStream inputStream = new BufferedInputStream(new FileInputStream("pom.xml"));

        RequestReceiver requestReceiver = new RequestReceiver(inputStream);
        System.out.println(requestReceiver.getArgsList());

        RequestInfoParser parser = new RequestInfoParser();
        System.out.println(parser.parseVersion("CONNECT ALL : tnkp/1.0"));
    }
}
