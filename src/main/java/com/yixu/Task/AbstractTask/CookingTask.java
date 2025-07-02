package com.yixu.Task.AbstractTask;

import org.bukkit.Location;

public abstract class CookingTask {

    protected Location location;
    protected int duration;

    private boolean manuallyFinished = false;

    public abstract void cookingTask();

    public CookingTask(Location location, int duration) {
        this.location = location;
        this.duration = duration;
    }

    public boolean isFinished() {
        return duration <= 0 || manuallyFinished;
    }

    public void forceFinished() {
        this.manuallyFinished = true;
    }


    public void setDuration(int duration) {
        this.duration = duration;
    }
}
