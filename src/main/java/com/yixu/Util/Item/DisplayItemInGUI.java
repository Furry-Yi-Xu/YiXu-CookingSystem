package com.yixu.Util.Item;

import com.github.stefvanschie.inventoryframework.gui.GuiItem;
import com.github.stefvanschie.inventoryframework.gui.type.ChestGui;
import com.github.stefvanschie.inventoryframework.pane.StaticPane;
import com.yixu.Config.RecipeConfig.RecipeConfig;
import com.yixu.Model.SlotAndAmount;
import org.bukkit.entity.Player;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Map;

public class DisplayItemInGUI {

    public void displayItemInGUI(Player player, Map<Integer, List<SlotAndAmount>> materialInInventory, String recipeName, InventoryView inventoryView) {

        RecipeConfig recipeConfig = new RecipeConfig(recipeName);

        for (int i = 0; i < materialInInventory.size(); i++) {
            List<SlotAndAmount> slotList = materialInInventory.get(i);

            int slot = slotList.getFirst().getSlot();
            ItemStack item = player.getInventory().getItem(slot).clone();
            item.setAmount(recipeConfig.getRecipeAmount(i));

            if (item != null) {
                slot = (i + 1) * 9 + 4;
                inventoryView.setItem(slot, item);
            }
        }
    }
}
