package com.yixu.CookingGUI;

import com.yixu.Builder.GUI.CookingGuiLayoutBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;


public class CookingGUI implements Listener {

    public void openCookingGUI(Player player) {

        CookingGuiLayoutBuilder cookingGuiLayoutBuilder = new CookingGuiLayoutBuilder();

        Inventory cookingGUI = cookingGuiLayoutBuilder.buildCookingGUILayout(player);

        player.openInventory(cookingGUI);
    }
}
