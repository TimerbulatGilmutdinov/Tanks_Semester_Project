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

import java.io.*;
import java.util.*;

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
    }

    private void setRequestInfoLine() {
        requestInfoLine = argsList.get(0);
    }

    public void setArgsList() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while((line = reader.readLine())!=null){
                argsList.add(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Map<String, String> getHeadersMap(List<String> argsList) throws IllegalHeaderNameException {
        for (String line : argsList) {
            if (line.contains(":")) {
                headersMap.put(headerParser.parseHeader(line), headerValueParser.parseValue(line));
            }
        }
        return headersMap;
    }

    public Request getRequest() throws IllegalHeaderNameException, IllegalProtocolInfoException {
        setArgsList();
        setRequestInfoLine();
        return Request.builder()
                .methodName(MethodName.valueOf(requestInfoParser.parseMethod(requestInfoLine)))
                .entity(Entity.valueOf(requestInfoParser.parseEntity(requestInfoLine)))
                .version(requestInfoParser.parseVersion(requestInfoLine))
                .headersMap(getHeadersMap(argsList))
                .build();
    }
}
