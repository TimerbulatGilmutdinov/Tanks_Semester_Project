package receivers;

import exceptions.IllegalHeaderNameException;
import parsers.HeaderParser;
import parsers.HeaderValueParser;
import protocol.Request;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class RequestReceiver {
    private final BufferedInputStream inputStream;
    private final List<String> argsList = new ArrayList<>();
    private final List<String> headersList = new ArrayList<>();
    private final List<String> headerValueList = new ArrayList<>();
    private final HeaderParser headerParser = new HeaderParser();
    private final HeaderValueParser headerValueParser = new HeaderValueParser();
    private final Map<String, String> headersMap = new HashMap<>();

    public RequestReceiver(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
    }

    public List<String> getArgsList() {
        String data = convertInputStreamDataToString();
        String[] res = data.split("\r?\n|\r");
        Collections.addAll(argsList,res);
        return argsList;
    }

    public String convertInputStreamDataToString() {
        String data;
        try {
            BufferedInputStream bStream = new BufferedInputStream(inputStream);
            ByteArrayOutputStream baous = new ByteArrayOutputStream();
            int temp = bStream.read();
            while (temp != -1) {
                baous.write((byte) temp);
                temp = bStream.read();
            }
            data = baous.toString("UTF-8");
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
        return data;
    }

    public void setHeadersMap(List<String> argsList) throws IllegalHeaderNameException {
        for (String line : argsList) {
            headersMap.put(headerParser.parseHeader(line), headerValueParser.parseValue(line));
        }
    }

    public Request getRequest() {
        return null;
    }
}
