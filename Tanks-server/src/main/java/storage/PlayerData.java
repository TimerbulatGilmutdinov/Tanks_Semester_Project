package storage;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class PlayerData {
    private final int id;
    private float tank_coord_x;
    private float tank_coord_y;
    private float tank_angle;
    private float turret_angle;
    private float bullet_direction_x;
    private float getBullet_direction_y;

    public PlayerData(int id, float tank_coord_x, float tank_coord_y, float tank_angle, float turret_angle, float bullet_direction_x, float getBullet_direction_y) {
        this.id = id;
        this.tank_coord_x = tank_coord_x;
        this.tank_coord_y = tank_coord_y;
        this.tank_angle = tank_angle;
        this.turret_angle = turret_angle;
        this.bullet_direction_x = bullet_direction_x;
        this.getBullet_direction_y = getBullet_direction_y;
    }
}
