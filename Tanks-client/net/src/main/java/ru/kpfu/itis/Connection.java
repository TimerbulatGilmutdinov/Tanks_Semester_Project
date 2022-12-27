package ru.kpfu.itis;

import constants.MethodName;
import constants.StatusCodes;
import protocol.Response;
import ru.kpfu.itis.exceptions.IllegalHeaderNameException;
import ru.kpfu.itis.exceptions.IllegalProtocolInfoException;
import ru.kpfu.itis.receivers.ResponseReceiver;
import ru.kpfu.itis.senders.RequestSender;
import ru.kpfu.itis.util.RequestBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class Connection {
    private final Socket socket;
    private RequestSender requestSender;
    private ResponseReceiver responseReceiver;

    public Connection() throws IOException {
        socket = new Socket("localhost", 8080);
        requestSender = new RequestSender(socket.getOutputStream());
        responseReceiver = new ResponseReceiver(socket.getInputStream());
    }

    public Socket searchMatch() throws IOException, InterruptedException, IllegalHeaderNameException, IllegalProtocolInfoException {
        RequestBuilder requestBuilder = new RequestBuilder();
        requestBuilder.getRequest(MethodName.CONNECT);
        requestSender.sendRequest(requestBuilder.getRequest(MethodName.CONNECT));
        Response response = responseReceiver.getResponse();
        if (response.getStatusCode() == StatusCodes.OK) {
            return socket;
        } else {
            return null;
        }
    }
}
