package ru.kpfu.itis.constant;

public enum Direction {
    UP(0, 1, 90),
    DOWN(0, -1, 270),
    LEFT(-1, 0, 180),
    RIGHT(1, 0, 0);

    private final int dirX; //direction by X
    private final int dirY; //direction by Y
    private final float angle;

    public float getAngle() {
        return angle;
    }

    public int getDirX() {
        return dirX;
    }

    public int getDirY() {
        return dirY;
    }

    Direction(int dirX, int dirY, float angle) {
        this.dirX = dirX;
        this.dirY = dirY;
        this.angle = angle;
    }
}
