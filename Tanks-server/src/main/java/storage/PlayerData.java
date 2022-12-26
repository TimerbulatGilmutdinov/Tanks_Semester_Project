package storage;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlayerData {
    private final int id;
    private final String name;
    private float tank_coord_x;
    private float tank_coord_y;
    private float tank_angle;
    private float turret_angle;
    private float bullet_direction_x;
    private float getBullet_direction_y;

    public PlayerData(int id, String name){
        this.id = id;
        this.name = name;
    }
}
