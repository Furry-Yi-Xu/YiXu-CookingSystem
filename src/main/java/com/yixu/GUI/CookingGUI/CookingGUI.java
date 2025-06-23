package com.yixu.GUI.CookingGUI;

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

    private static final List<String> buttonKeys = List.of("close", "start");

    public void openCookingGUI(Player player) {

        String cookingGUITitle = ConfigManager.getGuiConfig().getConfig().getString("cooking_title");
        Component formattedTitle = ComponentUtil.formatString(cookingGUITitle);

        Inventory cookingGUI = Bukkit.createInventory(new CookingGUIHolder(), 54, formattedTitle);

        for (int i = 0; i < buttonKeys.size(); i++) {
            String buttonType = CookingGUIConfig.getButtonType(buttonKeys.get(i));
            String buttonMaterial = CookingGUIConfig.getButtonMaterial(buttonKeys.get(i));
            int buttonSlot = CookingGUIConfig.getButtonSlot(buttonKeys.get(i));

            ItemStack itemStack = ItemStackResolver.getItemStack(buttonType, buttonMaterial);
            cookingGUI.setItem(buttonSlot, itemStack);
        }

        player.openInventory(cookingGUI);
    }
}
