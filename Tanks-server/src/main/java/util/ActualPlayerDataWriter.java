package util;

import constants.HeaderName;
import storage.PlayerData;

import java.util.Map;

public class ActualPlayerDataWriter {
    private Map<String, Float> headersMap;
    private PlayerData playerData;

    public ActualPlayerDataWriter(Map<String, Float> headersMap, PlayerData playerData) {
        this.headersMap = headersMap;
        this.playerData = playerData;
    }

    public void writePlayerData(){
        playerData.setTank_coord_x(headersMap.get(HeaderName.TANK_COORD_X.toString()));
        playerData.setTank_coord_y(headersMap.get(HeaderName.TANK_COORD_Y.toString()));
        playerData.setTank_angle(headersMap.get(HeaderName.TANK_ANGLE.toString()));
        playerData.setTurret_angle(headersMap.get(HeaderName.TURRET_ANGLE.toString()));
        playerData.setBullet_direction_x(headersMap.get(HeaderName.BULLET_DIRECTION_X.toString()));
        playerData.setTank_coord_y(headersMap.get(HeaderName.TANK_COORD_Y.toString()));
    }
}
