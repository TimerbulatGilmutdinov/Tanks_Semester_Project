package listeners;

import constants.MethodName;
import protocol.Request;
import server.Connection;
import storage.PlayerData;
import util.ActualPlayerDataWriter;

import java.util.Map;

public class PostListener extends AbstractServerEventListener{
    protected Map<Integer, PlayerData> playerDataMap = server.getAllPlayersDataMap();
    public PostListener() {
        super(MethodName.POST);
    }

    @Override
    public void handle(Connection connection, Request request) {
        PlayerData playerData = playerDataMap.get(connection.getId());
        ActualPlayerDataWriter playerDataWriter = new ActualPlayerDataWriter(request.getHeadersMap(),playerData);
        playerDataWriter.writePlayerData();
    }
}
