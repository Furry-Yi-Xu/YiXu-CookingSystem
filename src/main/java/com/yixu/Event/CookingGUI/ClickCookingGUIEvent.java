package com.yixu.Event.CookingGUI;

import com.yixu.Config.ConfigManager;
import com.yixu.GUI.CookingGUIManager;
import com.yixu.Model.RecipeIngredient;
import com.yixu.Util.Item.CheckItemLore;
import com.yixu.Util.Item.DisplayItemInGUI;
import com.yixu.Util.Message.MessageUtil;
import com.yixu.Util.Recipe.RecipeMaterialMapBuilder;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Set;

public class ClickCookingGUIEvent implements Listener {

    private final Plugin plugin;
    private final CookingGUIManager cookingGUIManager;

    private static final Set<InventoryAction> VALID_ACTIONS = Set.of(
            InventoryAction.PLACE_ALL,
            InventoryAction.PICKUP_ALL
    );

    public ClickCookingGUIEvent(Plugin plugin, CookingGUIManager cookingGUIManager) {
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
    }

    @EventHandler
    public void onClickCookingGUI(InventoryClickEvent event) {
        Component cookingGUITitle = MessageUtil.formatMessage("cooking.cooking_title");

        if (!event.getView().title().equals(cookingGUITitle)) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        InventoryAction action = event.getAction();

        if (!VALID_ACTIONS.contains(action)) {
            event.setCancelled(true);
            return;
        }

        if (event.getClickedInventory() == event.getView().getBottomInventory()) {
            event.setCancelled(false);
            return;
        }

        int slot = event.getRawSlot();
        if (slot != 4 && slot != 8) {
            event.setCancelled(true);
            return;
        }

        CheckItemLore checkItemLore = new CheckItemLore();

        if (slot == 4) {
            displayRecipeMaterial(event, player, checkItemLore);
            return;
        }

        if (slot == 8) {
            ItemStack recipeBook = event.getInventory().getItem(4);

            if (recipeBook == null || recipeBook.getType() == Material.AIR) {
                MessageUtil.sendMessage(player, "cooking.invalid_book");
                event.setCancelled(true);
                return;
            }

            String recipeName = checkItemLore.getRecipeBookName(recipeBook);
            if (recipeName == null) {
                MessageUtil.sendMessage(player, "cooking.invalid_book");
                event.setCancelled(true);
                return;
            }

            Location cookingTableLocation = cookingGUIManager.getCookingTableLocation(player.getUniqueId());
            startCooking(player, recipeBook, recipeName, cookingTableLocation);
            event.setCancelled(true);
            event.getInventory().close();
        }
    }

    private void displayRecipeMaterial(InventoryClickEvent event, Player player, CheckItemLore checkItemLore) {
        ItemStack cursorItem = event.getCursor();

        if (event.getAction() == InventoryAction.PICKUP_ALL) {
            event.setCancelled(false);
            for (int i = 0; i < 4; i++) {
                int index = (i + 1) * 9 + 4;
                event.getInventory().clear(index);
            }
            return;
        }

        if (cursorItem.getType() == Material.AIR) {
            return;
        }

        String recipeName = checkItemLore.getRecipeBookName(cursorItem);
        if (recipeName == null) {
            MessageUtil.sendMessage(player, "cooking.invalid_book");
            event.setCancelled(true);
            return;
        }

        RecipeMaterialMapBuilder builder = new RecipeMaterialMapBuilder();
        List<RecipeIngredient> ingredients = builder.buildMaterialMap(recipeName);
        if (ingredients == null || ingredients.isEmpty()) {
            MessageUtil.sendMessage(player, "cooking.invalid_material");
            event.setCancelled(true);
            return;
        }

        DisplayItemInGUI displayItemInGUI = new DisplayItemInGUI();
        displayItemInGUI.displayItemInGUI(player, ingredients, recipeName, event.getView());
    }

    private void startCooking(Player player, ItemStack recipeBook, String recipeName, Location guiLocation) {
        int stamina = ConfigManager.getRecipeConfig().getConfig().getInt(recipeName + ".recipe_book.stamina");

        ItemMeta meta = recipeBook.getItemMeta();
        if (!(meta instanceof Damageable itemMeta)) {
            MessageUtil.sendMessage(player, "cooking.invalid_book");
            return;
        }

        int maxDurability = itemMeta.getMaxDamage();
        int currentDurability = itemMeta.getDamage();
        int newDurability = currentDurability + stamina;

        if (newDurability >= maxDurability) {
            recipeBook.setAmount(0);
            MessageUtil.sendMessage(player, "cooking.cooking_book_destroy");
        } else {
            itemMeta.setDamage(newDurability);
            recipeBook.setItemMeta(itemMeta);
        }

        MessageUtil.sendMessage(player, "cooking.cooking_started");

        cookingGUIManager.setWorking(guiLocation, true);
    }
}
