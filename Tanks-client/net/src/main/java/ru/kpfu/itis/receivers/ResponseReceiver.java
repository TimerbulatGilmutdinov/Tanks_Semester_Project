package ru.kpfu.itis.receivers;

import constants.StatusCodes;
import exceptions.IllegalHeaderNameException;
import exceptions.IllegalProtocolInfoException;
import protocol.Response;
import ru.kpfu.itis.parsers.HeaderParser;
import ru.kpfu.itis.parsers.HeaderValueParser;
import ru.kpfu.itis.parsers.ResponseInfoParser;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseReceiver {
    private final BufferedInputStream inputStream;
    private final List<String> argsList = new ArrayList<>();
    private final HeaderParser headerParser = new HeaderParser();
    private final HeaderValueParser headerValueParser = new HeaderValueParser();
    private final ResponseInfoParser requestInfoParser = new ResponseInfoParser();
    private final Map<String, Float> headersMap = new HashMap<>();
    private String requestInfoLine;

    public ResponseReceiver(InputStream inputStream) {
        this.inputStream = new BufferedInputStream(inputStream);
    }

    private void setRequestInfoLine() {
        requestInfoLine = argsList.get(0);
    }

    private void setArgsList() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while (true) {
                line = reader.readLine();
                if (line == null || line.isEmpty()) {
                    return;
                }
                argsList.add(line);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Map<String, Float> getHeadersMap(List<String> argsList) throws exceptions.IllegalHeaderNameException {
        for (String line : argsList) {
            if (line.contains(":")) {
                headersMap.put(headerParser.parseHeader(line), Float.valueOf(headerValueParser.parseValue(line)));
            }
        }
        return headersMap;
    }

    public Response getResponse() throws IllegalProtocolInfoException, IllegalHeaderNameException {
        setArgsList();
        setRequestInfoLine();
        return Response.builder()
                .statusCode(requestInfoParser.parseStatusCode(requestInfoLine))
                .version(requestInfoParser.parseVersion(requestInfoLine))
                .headersMap(getHeadersMap(argsList))
                .build();
    }
}
