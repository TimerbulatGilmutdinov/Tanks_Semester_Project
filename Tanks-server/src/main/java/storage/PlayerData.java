package storage;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlayerData {
    private int id;
    private float tank_coord_x;
    private float tank_coord_y;
    private float tank_angle;
    private float turret_angle;
    private float bullet_direction_x;
    private float getBullet_direction_y;
}
