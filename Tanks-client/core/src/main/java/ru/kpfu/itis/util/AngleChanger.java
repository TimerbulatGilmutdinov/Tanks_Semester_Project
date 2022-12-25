package ru.kpfu.itis.util;

import static java.lang.Math.*;
import static java.lang.Math.abs;

public class AngleChanger {

    private static final float MEASUREMENT_ERROR = 1.5f;

    public static float getAngle(float x1, float y1, float x2, float y2) {
        float dx = x2 - x1;
        float dy = y2 - y1;
        return (float) toDegrees((float) atan2(dy, dx));
    }

    public static float makeRotation(float angle, float angleTo, float rotationSpeed, float dt) {
        if (abs(angle - angleTo) < MEASUREMENT_ERROR * rotationSpeed * dt) {
            angle = angleTo;
        } else if (angle < angleTo) {
            if (abs(angle - angleTo) < 180) {
                angle += rotationSpeed * dt;
            } else {
                angle -= rotationSpeed * dt;
            }
        } else if (angle > angleTo) {
            if (abs(angle - angleTo) < 180) {
                angle -= rotationSpeed * dt;
            } else {
                angle += rotationSpeed * dt;
            }
        }
        return angle;
    }

    public static float normalize(float angle) {
        while (angle < -180 || angle > 180) {
            if (angle > 180) {
                angle -= 360;
            } else {
                angle += 360;
            }
        }
        return angle;
    }
}
