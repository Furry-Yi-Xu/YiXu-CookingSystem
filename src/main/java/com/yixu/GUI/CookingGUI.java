package com.yixu.GUI;

import com.yixu.Event.CookingGUI.ClickCookingGUIEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;


public class CookingGUI implements Listener {

    public void openCookingGUI(Player player) {

        String cookingGUITitle = "§a烹饪界面";

        Inventory cookingGUI = Bukkit.createInventory(null, 54, Component.text(cookingGUITitle));

        player.openInventory(cookingGUI);

    }
}
