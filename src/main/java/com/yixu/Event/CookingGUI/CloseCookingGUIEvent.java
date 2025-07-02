package com.yixu.Event.CookingGUI;

import com.yixu.Cooking.CookingSessionManager;
import com.yixu.CookingGUI.CookingGUIHolder;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Cooking.CookingState;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;


public class CloseCookingGUIEvent implements Listener {

    private final CookingSessionManager cookingSessionManager;

    public CloseCookingGUIEvent(CookingSessionManager cookingSessionManager) {
        this.cookingSessionManager = cookingSessionManager;
    }

    @EventHandler
    public void onCloseCookingGUI(InventoryCloseEvent event) {

        if (!(event.getInventory().getHolder() instanceof CookingGUIHolder)) {
            return;
        }

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

        CookingSession playerSession = cookingSessionManager.getPlayerSession(player.getUniqueId());

        if (playerSession.getCookingState() == CookingState.WAITING_FOR_RECIPE_BOOK) {
            playerSession.setBoundPlayer(null);
        }

    }
}
