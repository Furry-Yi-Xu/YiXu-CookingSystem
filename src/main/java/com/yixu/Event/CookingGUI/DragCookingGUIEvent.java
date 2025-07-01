package com.yixu.Event.CookingGUI;

import com.yixu.CookingGUI.CookingGUIHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;

public class DragCookingGUIEvent implements Listener {

    @EventHandler
    public void onDragCookingGUI(InventoryDragEvent event) {

        if (!(event.getInventory().getHolder() instanceof CookingGUIHolder)) {
            return;
        }

        event.setCancelled(true);

    }
}
