package com.yixu.Event.CookingGUI;

import com.yixu.GUI.CookingGUIManager;
import com.yixu.Util.Message.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
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
    private final CookingGUIManager cookingGUIManager;

    public CloseCookingGUIEvent(Plugin plugin, CookingGUIManager cookingGUIManager) {
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
    }

    @EventHandler
    public void onCloseCookingGUI(InventoryCloseEvent event) {

        Component cookingGUITitle = MessageUtil.formatMessage("cooking.cooking_title");

        if (!event.getView().title().equals(cookingGUITitle)) {
            return;
        }

        Inventory inventory = event.getInventory();
        ItemStack item = inventory.getItem(4);
        HumanEntity player = event.getPlayer();
        Location playerOpenGUILocation = cookingGUIManager.getCookingTableLocation(player.getUniqueId());

        if (item != null && item.getType() != Material.AIR) {
            if (player.getInventory().firstEmpty() != -1) {
                player.getInventory().addItem(item);
            } else {
                player.getWorld().dropItem(player.getLocation(), item);
            }
        }

        cookingGUIManager.setUsed(playerOpenGUILocation, false);
    }
}
