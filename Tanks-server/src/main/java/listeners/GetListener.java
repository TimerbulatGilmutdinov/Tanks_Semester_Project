package listeners;

import constants.MethodName;
import protocol.Request;
import senders.ResponseSender;
import server.Connection;
import storage.PlayerData;
import util.ResponseBuilder;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

public class GetListener extends AbstractServerEventListener {
    public GetListener() {
        super(MethodName.GET);
    }

    @Override
    public void handle(Connection connection, Request request) {
        ResponseBuilder responseBuilder;
        Set<Integer> playersIdSet = server.getAllPlayersDataMap().keySet();
        Map<Integer, PlayerData> allPlayersDataMap = server.getAllPlayersDataMap();
        int sentRequestPlayerId = connection.getId();
        playersIdSet.remove(sentRequestPlayerId);
        ResponseSender responseSender = new ResponseSender(connection.getOutputStream());
        for (int requestedPlayerId : playersIdSet) {
            responseBuilder = new ResponseBuilder(allPlayersDataMap.get(requestedPlayerId));
            try {
                responseSender.sendResponse(responseBuilder.getResponse());
            } catch (IOException e) {
                throw new IllegalArgumentException(e);
            }
        }
    }
}
