package com.yixu.GUI.CookingGUI;

import com.yixu.Util.Message.MessageUtil;
import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;


public class CookingGUI implements Listener {

    public void openCookingGUI(Player player) {

        Component cookingGUITitle = MessageUtil.formatMessage("cooking.cooking_title");

        Inventory cookingGUI = Bukkit.createInventory(null, 54, cookingGUITitle);

        ItemStack itemStack = CustomStack.getInstance("customcrops_earth_3:stardust").getItemStack();

        cookingGUI.setItem(8, itemStack);

        player.openInventory(cookingGUI);

    }
}
