package com.yixu.Task.Abstract;

import org.bukkit.Location;

public abstract class CookingTask {

    protected Location location;

    protected int cookingDuration;
    protected int cookingValue;

    protected int fireValue;

    protected int fireMin;
    protected int fireMax;


    public abstract void cookingTask();

    public CookingTask(
            Location location,
            int cookingDuration,
            int cookingValue,
            int fireValue, int fireMin,
            int fireMax
    ) {
        this.location = location;
        this.cookingDuration = cookingDuration;
        this.cookingValue = cookingValue;
        this.fireValue = fireValue;
        this.fireMin = fireMin;
        this.fireMax = fireMax;
    }

    public boolean isCookingCompleted() {
        return cookingDuration <= 0 && cookingValue >= 100;
    }

    public boolean isCookingFailed() {
        return fireValue < fireMin || fireValue > fireMax;
    }

}
