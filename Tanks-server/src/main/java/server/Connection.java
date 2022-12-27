package server;

import constants.MethodName;
import exceptions.IllegalHeaderNameException;
import exceptions.IllegalProtocolInfoException;
import listeners.AbstractServerEventListener;
import listeners.ServerEventListener;
import lombok.Getter;
import protocol.Request;
import protocol.Response;
import receivers.RequestReceiver;
import senders.ResponseSender;
import storage.PlayerData;
import util.ResponseBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

@Getter
public class Connection implements Runnable {
    private static int count = 0;
    protected int id;
    protected Server server;
    protected Socket socket;
    protected RequestReceiver requestReceiver;
    protected ResponseSender responseSender;
    protected ResponseBuilder responseBuilder;
    protected InputStream inputStream;
    protected OutputStream outputStream;
    protected Map<Integer, PlayerData> allPlayersDataMap;

    public Connection(Server server, Socket socket) throws IOException {
        this.id = count++;
        this.server = server;
        this.socket = socket;
        inputStream = socket.getInputStream();
        outputStream = socket.getOutputStream();
        allPlayersDataMap = server.getAllPlayersDataMap();
        requestReceiver = new RequestReceiver(inputStream);
        responseSender = new ResponseSender(outputStream);
        responseBuilder = new ResponseBuilder(allPlayersDataMap.get(id));
    }

    @Override
    public void run() {
        Request request;
        Response response;

        try {
            try {
                while ((request = requestReceiver.getRequest()) != null) {
                    //TODO все поставить в правильный порядок
                    ServerEventListener listener = AbstractServerEventListener.getEventListener(
                            request.getMethodName());
                    listener.init(server);
                    response = responseBuilder.getResponse();
                    responseSender.sendResponse(response);
                    if (request.getMethodName() == MethodName.CONNECT) {
                        listener.handle(this, request);
                    }
                }
            } catch (IllegalHeaderNameException | IllegalProtocolInfoException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            this.server.removeConnection(this);
        }
    }
}
