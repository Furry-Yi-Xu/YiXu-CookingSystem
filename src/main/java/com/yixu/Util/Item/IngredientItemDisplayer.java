package com.yixu.Util.Item;

import com.yixu.Config.ConfigManager;
import com.yixu.Config.CookingConfig.CookingGUIConfig;
import com.yixu.Model.RecipeIngredientModel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Set;

public class IngredientItemDisplayer {

    public void displayItemInGUI(Player player, List<RecipeIngredientModel> recipeIngredientsListModel, String recipeName, InventoryView inventoryView) {

        ItemStack itemStack = null;

        int slotIndex = 0;

        for (RecipeIngredientModel recipeIngredientModel : recipeIngredientsListModel) {

            String ingredientType = recipeIngredientModel.getType();
            String ingredientMaterial = recipeIngredientModel.getMaterial();
            int ingredientAmount = recipeIngredientModel.getAmount();

            itemStack = ItemStackResolver.getItemStack(ingredientType, ingredientMaterial);

            itemStack.setAmount(ingredientAmount);

            int ingredientSlot = CookingGUIConfig.getButtonSlot("ingredient_" + (slotIndex + 1));
            inventoryView.setItem(ingredientSlot, itemStack);

            slotIndex++;
        }
    }
}
