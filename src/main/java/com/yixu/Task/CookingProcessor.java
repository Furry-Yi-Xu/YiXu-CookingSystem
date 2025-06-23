package com.yixu.Task;

import com.yixu.GUI.CookingGUIManager;
import com.yixu.Scheduler.BukkitAsyncScheduler;
import com.yixu.Scheduler.BukkitSyncScheduler;
import com.yixu.Task.Abstract.CookingTask;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;

public class CookingProcessor extends CookingTask {

    private final Plugin plugin;
    private final CookingGUIManager cookingGUIManager;
    private final BukkitSyncScheduler bukkitSyncScheduler;
    private final BukkitAsyncScheduler bukkitAsyncScheduler;

    public CookingProcessor(
            Location location,
            int cookingDuration,
            int cookingValue,
            int fireValue,
            int fireMin,
            int fireMax,
            Plugin plugin,
            CookingGUIManager cookingGUIManager,
            BukkitSyncScheduler bukkitSyncScheduler,
            BukkitAsyncScheduler bukkitAsyncScheduler
    ) {
        super(location, cookingDuration, cookingValue, fireValue, fireMin, fireMax);
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
        this.bukkitSyncScheduler = bukkitSyncScheduler;
        this.bukkitAsyncScheduler = bukkitAsyncScheduler;
    }

    @Override
    public void cookingTask() {

        cookingDuration--;



    }
}
