package receivers;

import constants.Entity;
import constants.MethodName;
import exceptions.IllegalHeaderNameException;
import exceptions.IllegalProtocolInfoException;
import lombok.Getter;
import parsers.HeaderParser;
import parsers.HeaderValueParser;
import parsers.RequestInfoParser;
import protocol.Request;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Getter
public class RequestReceiver {
    private final BufferedInputStream inputStream;
    private final List<String> argsList = new ArrayList<>();
    private final HeaderParser headerParser = new HeaderParser();
    private final HeaderValueParser headerValueParser = new HeaderValueParser();
    private final RequestInfoParser requestInfoParser = new RequestInfoParser();
    private final Map<String, String> headersMap = new HashMap<>();
    private String requestInfoLine;

    public RequestReceiver(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
        setArgsList();
        setFirstLine();
    }

    private void setArgsList() {
        String data = convertInputStreamDataToString();
        String[] res = data.split("\r?\n|\r");
        Collections.addAll(argsList,res);
    }

    private void setFirstLine(){
        requestInfoLine = argsList.get(0);
    }

    private String convertInputStreamDataToString() {
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

    public Map<String,String> getHeadersMap(List<String> argsList) throws IllegalHeaderNameException {
        for (String line : argsList) {
            if (line.contains(":")) {
                headersMap.put(headerParser.parseHeader(line), headerValueParser.parseValue(line));
            }
        }
        return headersMap;
    }

    public Request getRequest() throws IllegalHeaderNameException, IllegalProtocolInfoException {
        return Request.builder()
                .methodName(MethodName.valueOf(requestInfoParser.parseMethod(requestInfoLine)))
                .entity(Entity.valueOf(requestInfoParser.parseEntity(requestInfoLine)))
                .version(requestInfoParser.parseVersion(requestInfoLine))
                .headersMap(getHeadersMap(argsList))
                .build();
    }
}
