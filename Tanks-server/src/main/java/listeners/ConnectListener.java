package listeners;

import constants.MethodName;
import constants.ProtocolInfo;
import constants.StatusCodes;
import exceptions.ServerException;
import protocol.Request;
import protocol.Response;
import senders.ResponseSender;
import server.Connection;
import storage.PlayerData;

import java.util.Collections;

public class ConnectListener extends AbstractServerEventListener {
    public ConnectListener() {
        super(MethodName.CONNECT);
    }

    @Override
    public void handle(Connection connection, Request request) {
        PlayerData playerData = PlayerData.builder()
                .id(connection.getId())
                .tank_coord_x((float) Math.random() * 100)
                .tank_coord_y((float) Math.random() * 100)
                .tank_angle(0)
                .turret_angle(0)
                .bullet_direction_x(0)
                .getBullet_direction_y(0)
                .build();

        server.getAllPlayersDataMap().put(connection.getId(), playerData);
        Response response = Response.builder()
                .statusCode(StatusCodes.PLAYER_CONNECTED)
                .version(ProtocolInfo.VERSION)
                .headersMap(Collections.emptyMap())
                .build();
        ResponseSender responseSender = new ResponseSender(connection.getOutputStream());
        try {
            responseSender.sendResponse(response);
        } catch (ServerException e) {
            server.removeConnection(connection);
            throw new IllegalArgumentException(e);
        }
    }
}
