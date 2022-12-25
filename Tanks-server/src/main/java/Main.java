
import exceptions.IllegalHeaderNameException;
import exceptions.IllegalProtocolInfoException;
import parsers.HeaderParser;
import parsers.HeaderValueParser;
import parsers.RequestInfoParser;
import receivers.RequestReceiver;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, IllegalProtocolInfoException, IllegalHeaderNameException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream("test.txt"));

        RequestReceiver requestReceiver = new RequestReceiver(inputStream);
        System.out.println(requestReceiver.getRequest().getEntity());

        RequestInfoParser parser = new RequestInfoParser();
        System.out.println(parser.parseVersion("tnkp/1.0 CONNECT"));
        HeaderValueParser headerParser = new HeaderValueParser();
        System.out.println(headerParser.parseValue("TANK_COORD_X:5.9"));
        HeaderParser headerParser1 = new HeaderParser();
        System.out.println(headerParser1.parseHeader("TANK_COORD_X:11.8"));
    }
}
