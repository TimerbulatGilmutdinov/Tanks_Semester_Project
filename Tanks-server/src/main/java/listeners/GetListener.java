package listeners;

import constants.MethodName;
import protocol.Request;
import senders.ResponseSender;
import server.Connection;
import util.ResponseBuilder;

import java.io.IOException;
import java.util.Set;

public class GetListener extends AbstractServerEventListener {
    public GetListener() {
        super(MethodName.GET);
    }

    @Override
    public void handle(Connection connection, Request request) {
        Set<Integer> playersIdSet = server.getAllPlayersDataMap().keySet();
        int sentRequestPlayerId = connection.getId();
        playersIdSet.remove(sentRequestPlayerId);
        ResponseSender responseSender = new ResponseSender(connection.getOutputStream());
        for (int requestedPlayerId : playersIdSet) {
            ResponseBuilder responseBuilder = new ResponseBuilder(server.getAllPlayersDataMap().get(requestedPlayerId));
            try {
                responseSender.sendResponse(responseBuilder.getResponse());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
