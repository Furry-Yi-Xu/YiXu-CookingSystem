package com.yixu;

import com.yixu.Command.CommandManager;
import com.yixu.Command.MainCommand.MainTabCompleter;
import com.yixu.Config.ConfigManager;
import com.yixu.Event.EventManager;
import com.yixu.GUI.CookingGUIManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class CookingSystem extends JavaPlugin {

    public CookingSystem() {
        super();
    }

    @Override
    public void onEnable() {
        saveDefaultConfig();

        CookingGUIManager cookingGUIManager = new CookingGUIManager();

        ConfigManager.init(this);
        EventManager.init(this, cookingGUIManager);

        getCommand("yixu-cookingsystem").setExecutor(new CommandManager());
        getCommand("yixu-cookingsystem").setTabCompleter(new MainTabCompleter());

        getLogger().info("YiXu-CookingSystem 插件已加载！");
    }

    @Override
    public void onDisable() {
        getLogger().info("YiXu-CookingSystem 插件已卸载！");
    }
}
