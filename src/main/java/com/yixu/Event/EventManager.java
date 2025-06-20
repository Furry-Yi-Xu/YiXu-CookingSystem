package com.yixu.Event;

import com.yixu.Event.CookingGUI.ClickCookingGUIEvent;
import com.yixu.Event.CookingGUI.CloseCookingGUIEvent;
import com.yixu.Event.CookingGUI.DragCookingGUIEvent;
import com.yixu.Event.ItemsAdder.CustomBlockInteractEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.sql.Connection;

public class EventManager {

    public static void init(Plugin plugin) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CustomBlockInteractEvent(plugin), plugin);

        pluginManager.registerEvents(new ClickCookingGUIEvent(plugin), plugin);
        pluginManager.registerEvents(new DragCookingGUIEvent(plugin), plugin);
        pluginManager.registerEvents(new CloseCookingGUIEvent(plugin), plugin);
    }
}
