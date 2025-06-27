package com.yixu;

import com.yixu.Command.CommandManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.Config.ConfigManager;
import com.yixu.Event.EventManager;
import com.yixu.GUI.CookingGUIManager;
import com.yixu.Scheduler.BukkitAsyncScheduler;
import com.yixu.Scheduler.CookingTaskSyncScheduler;
import com.yixu.Util.Language.ItemNameTranslator;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

public final class CookingSystem extends JavaPlugin {

    private static CookingSystem instance;

    public CookingSystem() {
        super();
    }

    @Override
    public void onEnable(){
        instance = this;

        saveDefaultConfig();

        CookingTaskSyncScheduler cookingTaskSyncScheduler = new CookingTaskSyncScheduler();
        BukkitAsyncScheduler bukkitAsyncScheduler = new BukkitAsyncScheduler();

        cookingTaskSyncScheduler.runTaskTimer(this, 0, 20L);
        bukkitAsyncScheduler.runTaskTimer(this, 0, 20L);

        CookingGUIManager cookingGUIManager = new CookingGUIManager();

        try {
            ConfigManager.init(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        EventManager.init(this, cookingGUIManager, cookingTaskSyncScheduler, bukkitAsyncScheduler);


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

}
