package com.yixu.Util.Item;

import com.yixu.Config.RecipeConfig.RecipeConfig;
import com.yixu.Model.RecipeIngredient;
import dev.lone.itemsadder.api.CustomStack;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DisplayItemInGUI {

    public void displayItemInGUI(Player player, List<RecipeIngredient> recipeIngredientsList, String recipeName, InventoryView inventoryView) {

        ItemStack itemStack = null;

        for (int i = 0; i < recipeIngredientsList.size(); i++) {

            RecipeIngredient recipeIngredient = recipeIngredientsList.get(i);

            String ingredientType = recipeIngredient.getType();
            String ingredientMaterial = recipeIngredient.getMaterial();
            int ingredientAmount = recipeIngredient.getAmount();

            switch (ingredientType) {
                case "itemsadder":
                    itemStack = CustomStack.getInstance(ingredientMaterial).getItemStack();
                    break;
                case "minecraft":
                    Material material = Material.matchMaterial(ingredientMaterial.toUpperCase());
                    itemStack = new ItemStack(material);
                    break;
            }

            itemStack.setAmount(ingredientAmount);

            if (itemStack != null) {
                int slot = (i + 1) * 9 + 4;
                inventoryView.setItem(slot, itemStack);
            }
        }
    }
}
