package com.yixu.Event.CookingGUI;

import com.yixu.CookingPot.Holder.CookingGUIHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;

public class DragCookingGUIEvent implements Listener {

    private final Plugin plugin;

    public DragCookingGUIEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDragCookingGUI(InventoryDragEvent event) {

        if (!(event.getInventory().getHolder() instanceof CookingGUIHolder)) {
            return;
        }

        event.setCancelled(true);

    }
}
