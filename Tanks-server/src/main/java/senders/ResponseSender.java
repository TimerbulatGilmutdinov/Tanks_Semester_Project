package senders;

import exceptions.ServerException;
import protocol.Response;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

public class ResponseSender {
    private final BufferedOutputStream outputStream;

    public ResponseSender(OutputStream outputStream) {
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public void sendResponse(Response response) throws ServerException {
        Map<String, Float> headersMap = response.getHeadersMap();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        try {
            writer.write(response.toString());
        }catch (IOException e){
            throw new ServerException(e);
        }
    }
}
