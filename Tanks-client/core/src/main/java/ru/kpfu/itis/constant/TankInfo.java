package ru.kpfu.itis.constant;

public enum TankInfo {
    RED("red_tank", "red_turret"),
    GREEN("green_tank", "green_turret"),
    BLUE("blue_tank", "blue_turret"),
    YELLOW("yellow_tank", "yellow_turret");

    private final String name;
    private final String turretFileName;

    public String getRegionName() {
        return name;
    }

    public String getTurretRegionName() {
        return turretFileName;
    }

    TankInfo(String name, String turretFileName) {
        this.name = name;
        this.turretFileName = turretFileName;
    }
}
