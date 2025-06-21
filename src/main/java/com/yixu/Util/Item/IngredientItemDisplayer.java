package com.yixu.Util.Item;

import com.yixu.Config.ConfigManager;
import com.yixu.Model.RecipeIngredientModel;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class IngredientItemDisplayer {

    public void displayItemInGUI(Player player, List<RecipeIngredientModel> recipeIngredientsListModel, String recipeName, InventoryView inventoryView) {

        ItemStack itemStack = null;

        List<Integer> ingredientSlots = ConfigManager.getGuiConfig().getConfig().getIntegerList("ingredient_slots");

        for (int i = 0; i < recipeIngredientsListModel.size(); i++) {

            RecipeIngredientModel recipeIngredientModel = recipeIngredientsListModel.get(i);

            String ingredientType = recipeIngredientModel.getType();
            String ingredientMaterial = recipeIngredientModel.getMaterial();
            int ingredientAmount = recipeIngredientModel.getAmount();

            itemStack = ItemStackResolver.getItemStack(ingredientType, ingredientMaterial);

            itemStack.setAmount(ingredientAmount);

            if (itemStack != null) {
                int slot = ingredientSlots.get(i);
                inventoryView.setItem(slot, itemStack);
            }
        }
    }
}
