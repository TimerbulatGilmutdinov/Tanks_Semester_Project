package receivers;

import constants.MethodName;
import parsers.HeaderParser;
import parsers.HeaderValueParser;
import protocol.Content;
import protocol.Message;
import protocol.Request;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class RequestReceiver {
    private final BufferedInputStream inputStream;
    private Content content;
    private List<String> argsList;
    private List<String> headersList;
    private List<String> headerValueList;
    private HeaderParser headerParser = new HeaderParser();
    private HeaderValueParser headerValueParser = new HeaderValueParser();
    private Map<String, String> headersMap = new HashMap<>();

    public RequestReceiver(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
    }

    public String[] getArgsList() {
        String data = convertInputStreamDataToString();
        String[] res = data.split("\r?\n|\r");
        return res;
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

    public void setHeadersMap(List<String> argsList) {
        for (String line : argsList) {
            headersMap.put(headerParser.parseHeader(line), headerValueParser.parseValue(line));
        }
    }

    public Request getRequest(Message message) {
        return new Request(MethodName.GET_ALL, headersMap);
    }
}
