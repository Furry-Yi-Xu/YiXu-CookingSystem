package com.yixu.Event;

import com.yixu.Event.CookingGUI.ClickCookingGUIEvent;
import com.yixu.Event.CookingGUI.CloseCookingGUIEvent;
import com.yixu.Event.CookingGUI.DragCookingGUIEvent;
import com.yixu.Event.ItemsAdder.CustomBlockBreakEvent;
import com.yixu.Event.ItemsAdder.CustomBlockInteractEvent;
import com.yixu.Event.ItemsAdder.CustomBlockPlaceEvent;
import com.yixu.GUI.CookingGUIManager;
import com.yixu.Scheduler.BukkitAsyncScheduler;
import com.yixu.Scheduler.BukkitSyncScheduler;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

public class EventManager {

    public static void init(
            Plugin plugin,
            CookingGUIManager cookingGUIManager,
            BukkitSyncScheduler bukkitSyncScheduler,
            BukkitAsyncScheduler bukkitAsyncScheduler
    ) {
        PluginManager pluginManager = plugin.getServer().getPluginManager();

        pluginManager.registerEvents(new CustomBlockInteractEvent(plugin, cookingGUIManager), plugin);
        pluginManager.registerEvents(new CustomBlockBreakEvent(plugin, cookingGUIManager), plugin);
        pluginManager.registerEvents(new CustomBlockPlaceEvent(plugin), plugin);

        pluginManager.registerEvents(new ClickCookingGUIEvent(plugin, cookingGUIManager, bukkitSyncScheduler, bukkitAsyncScheduler), plugin);
        pluginManager.registerEvents(new DragCookingGUIEvent(plugin), plugin);
        pluginManager.registerEvents(new CloseCookingGUIEvent(plugin, cookingGUIManager), plugin);
    }
}
