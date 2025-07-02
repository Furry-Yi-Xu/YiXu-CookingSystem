package com.yixu;

import com.yixu.Command.CommandManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.Config.ConfigManager;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Event.EventManager;
import com.yixu.Scheduler.CookingTaskSyncScheduler;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

public final class CookingSystem extends JavaPlugin {

    private static CookingSystem instance;

    private CookingTaskSyncScheduler cookingTaskSyncScheduler;

    public CookingSystem() {
        super();
    }

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        cookingTaskSyncScheduler = new CookingTaskSyncScheduler();
        cookingTaskSyncScheduler.start(this);

        CookingSessionManager cookingSessionManager = new CookingSessionManager();

        try {
            ConfigManager.init(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        EventManager.init(this, cookingSessionManager, cookingTaskSyncScheduler);

        getCommand("yixu-cookingsystem").setExecutor(new CommandManager());
        getCommand("yixu-cookingsystem").setTabCompleter(new MainTabCompleter());

        getLogger().info("YiXu-CookingSystem 插件已加载！");

    }

    @Override
    public void onDisable() {
        getLogger().info("YiXu-CookingSystem 插件已卸载！");
    }

    public static CookingSystem getInstance() {
        return instance;
    }

    public CookingTaskSyncScheduler getCookingTaskSyncScheduler() {
        return cookingTaskSyncScheduler;
    }

    public void setCookingTaskSyncScheduler(CookingTaskSyncScheduler cookingTaskSyncScheduler) {
        this.cookingTaskSyncScheduler = cookingTaskSyncScheduler;
    }
}
