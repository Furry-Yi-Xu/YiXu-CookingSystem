package com.yixu.Event.ItemsAdder;

import com.yixu.GUI.CookingGUIManager;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class CustomBlockBreakEvent implements Listener {

    private final Plugin plugin;
    private final CookingGUIManager cookingGUIManager;

    public CustomBlockBreakEvent(Plugin plugin, CookingGUIManager cookingGUIManager) {
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
    }

    @EventHandler
    public void onCustomBlockInteractEvent(dev.lone.itemsadder.api.Events.CustomBlockBreakEvent event){

        if (cookingGUIManager.isUsed(event.getBlock().getLocation())) {
            event.setCancelled(true);
            MessageUtil.sendMessage(event.getPlayer(), "cooking.cooking_used_break");
            return;
        }

        if (cookingGUIManager.isWorking(event.getBlock().getLocation())) {
            event.setCancelled(true);
            MessageUtil.sendMessage(event.getPlayer(), "cooking.cooking_started_break");
            return;
        }

    }
}
