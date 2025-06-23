package com.yixu.GUI.CookingGUI;

import com.yixu.Builder.GUI.CookingGuiLayoutBuilder;
import com.yixu.Config.ConfigManager;
import com.yixu.Config.CookingConfig.CookingGUIConfig;
import com.yixu.GUI.Holder.CookingGUIHolder;
import com.yixu.Util.Item.ItemStackResolver;
import com.yixu.Util.Message.ComponentUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;


public class CookingGUI implements Listener {

    public void openCookingGUI(Player player) {

        CookingGuiLayoutBuilder cookingGuiLayoutBuilder = new CookingGuiLayoutBuilder();

        Inventory cookingGUI = cookingGuiLayoutBuilder.buildCookingGUILayout(player);

        player.openInventory(cookingGUI);
    }
}
