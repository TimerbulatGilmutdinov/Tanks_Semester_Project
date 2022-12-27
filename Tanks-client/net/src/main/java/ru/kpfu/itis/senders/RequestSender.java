package ru.kpfu.itis.senders;

import protocol.Request;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class RequestSender {
    private final BufferedOutputStream outputStream;

    public RequestSender(OutputStream outputStream) {
        this.outputStream = new BufferedOutputStream(outputStream);
    }

    public void sendRequest(Request request) throws IOException {
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        writer.write(request.toString());
    }
}
