package com.yixu.Util.Item;

import dev.lone.itemsadder.api.CustomStack;
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
}
