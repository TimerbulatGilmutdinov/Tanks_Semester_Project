package senders;

import parsers.HeaderParser;
import parsers.HeaderValueParser;
import parsers.RequestInfoParser;
import storage.PlayerData;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseSender {
    private final BufferedOutputStream outputStream;
    private final List<String> argsList = new ArrayList<>();
    private final HeaderParser headerParser = new HeaderParser();
    private final HeaderValueParser headerValueParser = new HeaderValueParser();
    private final RequestInfoParser requestInfoParser = new RequestInfoParser();
    private final Map<String, Float> headersMap = new HashMap<>();
    private PlayerData playerData;
    private String requestInfoLine;

    public ResponseSender(OutputStream outputStream){
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public void sendResponse(){

    }



}
