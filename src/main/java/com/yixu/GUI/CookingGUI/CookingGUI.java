package com.yixu.GUI.CookingGUI;

import com.yixu.Config.ConfigManager;
import com.yixu.Util.Message.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;


public class CookingGUI implements Listener {

    public void openCookingGUI(Player player) {

        Component cookingGUITitle = MessageUtil.formatMessage("cooking.cooking_title");

        Inventory cookingGUI = Bukkit.createInventory(null, 54, cookingGUITitle);

        player.openInventory(cookingGUI);

    }
}
