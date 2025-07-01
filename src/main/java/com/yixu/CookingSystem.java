package com.yixu;

import com.yixu.Command.CommandManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.Config.ConfigManager;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Event.EventManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.FileNotFoundException;

public final class CookingSystem extends JavaPlugin {

    private static CookingSystem instance;

    public CookingSystem() {
        super();
    }

    @Override
    public void onEnable() {

        saveDefaultConfig();

        CookingSessionManager cookingSessionManager = new CookingSessionManager();

        try {
            ConfigManager.init(this);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        EventManager.init(this, cookingSessionManager);

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
