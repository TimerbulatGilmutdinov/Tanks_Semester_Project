package server;

import constants.MethodName;
import exceptions.IllegalHeaderNameException;
import exceptions.IllegalProtocolInfoException;
import exceptions.ServerException;
import listeners.AbstractServerEventListener;
import listeners.ServerEventListener;
import lombok.Getter;
import protocol.Request;
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
    private static int count = 1;
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
        int connectRequestCount = 0;
        try {
            while ((request = requestReceiver.getRequest()) != null) {
                ServerEventListener listener = AbstractServerEventListener.getEventListener(
                        request.getMethodName());
                listener.init(server);
                if (request.getMethodName() == MethodName.CONNECT && connectRequestCount <= 1) {
                    listener.handle(this, request);
                    connectRequestCount++;
                } else {
                    throw new ServerException("One client can only connect 1 time during the game session!");
                }
                if (request.getMethodName() == MethodName.GET) {
                    listener.handle(this, request);
                }
                if (request.getMethodName() == MethodName.POST) {
                    listener.handle(this, request);
                }
            }
        } catch (IllegalHeaderNameException | IllegalProtocolInfoException | ServerException e) {
            e.printStackTrace();
        }
    }
}
