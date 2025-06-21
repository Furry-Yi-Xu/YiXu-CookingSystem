package com.yixu.Event.CookingGUI;

import com.yixu.Config.ConfigManager;
import com.yixu.Model.RecipeIngredient;
import com.yixu.Model.SlotAndAmount;
import com.yixu.Util.Item.CheckItemLore;
import com.yixu.Util.Item.CheckItemPut;
import com.yixu.Util.Item.DisplayItemInGUI;
import com.yixu.Util.Message.MessageUtil;
import com.yixu.Util.Recipe.RecipeMaterialMapBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClickCookingGUIEvent implements Listener {

    private final Plugin plugin;

    public ClickCookingGUIEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    private static final Set<InventoryAction> VALID_ACTIONS = Set.of(
            InventoryAction.PLACE_ALL,
            InventoryAction.PICKUP_ALL
    );

    @EventHandler
    public void onClickCookingGUI(InventoryClickEvent event) {

        Component cookingGUITitle = MessageUtil.formatMessage("cooking.cooking_title");

        if (!event.getView().title().equals(cookingGUITitle)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();

        if (!VALID_ACTIONS.contains(event.getAction())) {
            event.setCancelled(true);
            return;
        }

        if (event.getAction() == null || event.getClickedInventory() != event.getView().getTopInventory()) {
            return;
        }

        int slot = event.getRawSlot();

        if (slot != 4) {
            event.setCancelled(true);
            return;
        }

        if (event.getAction() == InventoryAction.PICKUP_ALL) {
            event.setCancelled(false);

            for (int i = 0; i < 4; i++) {
                int index = (i + 1) * 9 + 4;
                event.getInventory().clear(index);
            }

            return;
        }

        ItemStack cursorItem = event.getCursor();

        if (cursorItem == null || cursorItem.getType() == Material.AIR) {
            return;
        }

        CheckItemLore checkItemLore = new CheckItemLore();

        String recipeName = checkItemLore.getRecipeBookName(cursorItem);

        if (recipeName == null) {
            MessageUtil.sendMessage(player, "cooking.invalid_book");
            event.setCancelled(true);
            return;
        }

        RecipeMaterialMapBuilder recipeMaterialMapBuilder = new RecipeMaterialMapBuilder();
        List<RecipeIngredient> recipeIngredientsList = recipeMaterialMapBuilder.buildMaterialMap(recipeName);

        if (recipeIngredientsList == null || recipeIngredientsList.isEmpty()) {
            MessageUtil.sendMessage(player, "cooking.invalid_material");
            return;
        }

        CheckItemPut checkItemPut = new CheckItemPut();
        Map<Integer, List<SlotAndAmount>> materialInInventory = checkItemPut.findMaterialInInventory(player, recipeIngredientsList);

        if (materialInInventory == null || materialInInventory.isEmpty()) {
            return;
        }

        DisplayItemInGUI displayItemInGUI = new DisplayItemInGUI();
        displayItemInGUI.displayItemInGUI(player, materialInInventory, recipeName, event.getView());

        event.setCancelled(false);
    }
}


