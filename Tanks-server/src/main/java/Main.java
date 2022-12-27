import exceptions.IllegalHeaderNameException;
import exceptions.IllegalProtocolInfoException;
import receivers.RequestReceiver;

import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException, IllegalProtocolInfoException, IllegalHeaderNameException {
        InputStream inputStream = new BufferedInputStream(new FileInputStream("test.txt"));

        RequestReceiver requestReceiver = new RequestReceiver(inputStream);
        System.out.println(requestReceiver.getRequest());
    }
}
