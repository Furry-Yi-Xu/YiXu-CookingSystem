package com.yixu.Util.Item;

import com.yixu.Config.ConfigManager;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class RecipeBookNameProvider {
    public String getRecipeBookName(ItemStack item) {

        if (item == null || !item.hasItemMeta()) {
            return null;
        }

        ItemMeta itemMeta = item.getItemMeta();

        if (itemMeta == null || !itemMeta.hasLore()) {
            return null;
        }

        List<Component> itemLores = itemMeta.lore();

        if (itemLores == null || itemLores.isEmpty()) {
            return null;
        }

        List<String> recipeList = ConfigManager.getConfig().getConfig().getStringList("recipe_list");

        for (Component lore : itemLores) {
            String serializeLore = PlainTextComponentSerializer.plainText().serialize(lore);
            for (String recipeName : recipeList) {
                if (serializeLore.contains(recipeName)) {
                    return recipeName;
                }
            }
        }
        return null;
    }
}
