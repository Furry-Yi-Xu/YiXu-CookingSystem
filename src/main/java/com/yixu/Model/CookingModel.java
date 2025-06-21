package com.yixu.Model;

import org.bukkit.Location;

public class CookingModel {

    private final Location location;
    private boolean isUsed = false;
    private boolean isWorking = false;

    public CookingModel(Location location, boolean isUsed, boolean isWorking) {
        this.location = location;
        this.isUsed = isUsed;
        this.isWorking = isWorking;
    }

    public CookingModel(Location location) {
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
