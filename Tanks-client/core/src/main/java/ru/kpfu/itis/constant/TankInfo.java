package ru.kpfu.itis.constant;

public enum TankInfo {
    RED("red_tank.png", "red_turret.png"),
    GREEN("green_tank.png", "green_turret.png"),
    BLUE("blue_tank.png", "blue_turret.png"),
    YELLOW("yellow_tank.png", "yellow_turret.png");

    private final String name;
    private final String turretFileName;

    public String getFileName() {
        return name;
    }

    public String getTurretFileName() {
        return turretFileName;
    }

    TankInfo(String name, String turretFileName) {
        this.name = name;
        this.turretFileName = turretFileName;
    }
}
