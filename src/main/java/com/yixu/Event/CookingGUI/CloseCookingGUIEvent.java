package com.yixu.Event.CookingGUI;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


public class CloseCookingGUIEvent implements Listener {

    private final Plugin plugin;

    public CloseCookingGUIEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCloseCookingGUI(InventoryCloseEvent event) {

        Inventory inventory = event.getInventory();
        ItemStack item = inventory.getItem(4);

        HumanEntity player = event.getPlayer();

        if (item != null && item.getType() != Material.AIR) {
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(item);
            } else {
                player.getWorld().dropItem(player.getLocation(), item);
            }
        }

    }
}
