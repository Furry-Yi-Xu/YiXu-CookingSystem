package com.yixu.Task.AbstractTask;

import org.bukkit.Location;

import java.util.HashMap;

public abstract class CookingTask {

    protected Location location;
    protected int duration;

    public abstract void cookingTask();

    public CookingTask(Location location, int duration) {
        this.location = location;
        this.duration = duration;
    }

    public boolean isFinished() {
        return duration <= 0;
    }

}
