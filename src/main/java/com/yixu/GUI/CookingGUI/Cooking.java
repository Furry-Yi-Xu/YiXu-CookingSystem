package com.yixu.GUI.CookingGUI;

import org.bukkit.Location;

public class Cooking {

    private final Location location;
    private boolean isUsed = false;
    private boolean isWorking = false;

    public Cooking(Location location, boolean isUsed, boolean isWorking) {
        this.location = location;
        this.isUsed = isUsed;
        this.isWorking = isWorking;
    }

    public Cooking(Location location) {
        this.location = location;
        this.isUsed = false;
        this.isWorking = false;
    }

    public Location getLocation() {
        return location;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void setWorking(boolean isWorking) {
        this.isWorking = isWorking;
    }
}
