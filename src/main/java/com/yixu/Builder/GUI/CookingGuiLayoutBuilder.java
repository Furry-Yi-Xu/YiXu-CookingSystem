package com.yixu.Builder.GUI;

import com.yixu.Config.CookingConfig.CookingGUIConfig;
import com.yixu.CookingGUI.CookingGUIHolder;
import com.yixu.Util.Message.ComponentUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Set;

public class CookingGuiLayoutBuilder {

    public Inventory buildCookingGUILayout(Player player) {

        String guiTitle = CookingGUIConfig.getTitle();
        Set<String> guiIcons = CookingGUIConfig.getIcons();

        Component formattedTitle = ComponentUtil.formatString(guiTitle);

        Inventory cookingGUI = Bukkit.createInventory(new CookingGUIHolder(), 54, formattedTitle);

        for (int i = 0; i < guiIcons.size(); i++) {
            for (String guiIcon : guiIcons) {
                ItemStack itemStack = buildButtonItemstack(guiIcon);
                int buttonSlot = CookingGUIConfig.getButtonSlot(guiIcon);
                cookingGUI.setItem(buttonSlot, itemStack);
            }
        }
        return cookingGUI;
    }

    public ItemStack buildButtonItemstack(String key) {

        String material = CookingGUIConfig.getMaterial(key);
        String name = CookingGUIConfig.getName(key);
        int modelData = CookingGUIConfig.getModelData(key);
        List<String> lore = CookingGUIConfig.getLore(key);

        if (material == null || material.equals("air")) {
            return new ItemStack(Material.AIR);
        }

        Component formattedName = ComponentUtil.formatString(name);
        List<Component> formattedLore = ComponentUtil.formatList(lore);

        ItemStack itemStack = new ItemStack(Material.valueOf(material.toUpperCase()));

        ItemMeta itemMeta = itemStack.getItemMeta();

        itemMeta.displayName(formattedName);
        itemMeta.lore(formattedLore);
        itemMeta.setCustomModelData(modelData);

        itemStack.setItemMeta(itemMeta);

        return itemStack;
    }

}
