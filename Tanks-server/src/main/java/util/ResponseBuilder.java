package util;

import constants.HeaderName;
import constants.ProtocolInfo;
import constants.StatusCodes;
import protocol.Response;
import storage.PlayerData;

import java.util.HashMap;
import java.util.Map;

public class ResponseBuilder {
    private final Map<String, Float> headersMap = new HashMap<>();
    private final PlayerData playerData;

    public ResponseBuilder(PlayerData playerData){
        this.playerData = playerData;
        setHeadersMap();
    }

    private void setHeadersMap() {
        headersMap.put(HeaderName.TANK_COORD_X.toString(), playerData.getTank_coord_x());
        headersMap.put(HeaderName.TANK_COORD_Y.toString(), playerData.getTank_coord_y());
        headersMap.put(HeaderName.TURRET_ANGLE.toString(), playerData.getTank_angle());
        headersMap.put(HeaderName.TURRET_ANGLE.toString(), playerData.getTurret_angle());
        headersMap.put(HeaderName.BULLET_DIRECTION_X.toString(), playerData.getBullet_direction_x());
        headersMap.put(HeaderName.BULLET_DIRECTION_Y.toString(), playerData.getGetBullet_direction_y());
    }

    public Response getResponse(){
        return Response.builder()
                .statusCode(StatusCodes.OK)
                .version(ProtocolInfo.VERSION)
                .headersMap(headersMap)
                .build();
    }
}
