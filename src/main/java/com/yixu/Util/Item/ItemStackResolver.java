package com.yixu.Util.Item;

import com.yixu.Util.Language.ItemNameTranslator;
import dev.lone.itemsadder.api.CustomStack;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TranslatableComponent;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemStackResolver {

    public static ItemStack getItemStack(String typeName, String itemName) {

        ItemStack itemStack = null;

        switch (typeName) {
            case "itemsadder":
                itemStack = getIAItem(itemName);
                break;
            case "minecraft":
                itemStack = getVanillaItem(itemName);
                break;
        }

        return itemStack;
    }

    public static ItemStack getIAItem(String namespacedID) {
        return CustomStack.getInstance(namespacedID).getItemStack();
    }

    public static ItemStack getVanillaItem(String itemName) {
        Material material = Material.matchMaterial(itemName.toUpperCase());
        return new ItemStack(material);
    }

    public static String getItemDisplayName(String typeName, ItemStack itemStack) {
        String itemDisplayName = null;

        switch (typeName) {
            case "itemsadder":
                itemDisplayName = getIAItemDisplayName(itemStack);
                break;
            case "minecraft":
                itemDisplayName = getVanillaDisplayName(itemStack);
                break;
        }

        return itemDisplayName;
    }

    public static String getIAItemDisplayName(ItemStack itemStack) {
        return CustomStack.byItemStack(itemStack).getDisplayName();
    }

    public static String getVanillaDisplayName(ItemStack itemStack) {
        return ItemNameTranslator.getItemName(itemStack);
    }
}
