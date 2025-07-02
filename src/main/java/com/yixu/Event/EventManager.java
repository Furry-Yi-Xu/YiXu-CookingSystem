package com.yixu.Event;

import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Event.CookingGUI.ClickCookingGUIEvent;
import com.yixu.Event.CookingGUI.CloseCookingGUIEvent;
import com.yixu.Event.CookingGUI.DragCookingGUIEvent;
import com.yixu.Event.ItemsAdder.CustomBlockBreakEvent;
import com.yixu.Event.ItemsAdder.CustomBlockInteractEvent;
import com.yixu.Event.ItemsAdder.CustomBlockPlaceEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public static void init(
            Plugin plugin,
            CookingSessionManager cookingSessionManager
    ) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CustomBlockPlaceEvent(), plugin);
        pluginManager.registerEvents(new CustomBlockBreakEvent(cookingSessionManager), plugin);
        pluginManager.registerEvents(new CustomBlockInteractEvent(cookingSessionManager), plugin);

        pluginManager.registerEvents(new DragCookingGUIEvent(), plugin);
        pluginManager.registerEvents(new ClickCookingGUIEvent(cookingSessionManager), plugin);
        pluginManager.registerEvents(new CloseCookingGUIEvent(cookingSessionManager), plugin);
    }
}
