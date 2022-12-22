package protocol;

import constants.MethodName;
import lombok.Getter;

@Getter
public class GetAllMethod extends Message {
    private MethodName methodName;
    private float xTankCoordinate;
    private float yTankCoordinate;
    private float xBulletDirection;
    private float yBulletDirection;
    private float weaponAngle;
}
