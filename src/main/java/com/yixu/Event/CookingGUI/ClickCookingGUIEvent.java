package com.yixu.Event.CookingGUI;

import com.yixu.Builder.Recipe.RecipeMaterialMapBuilder;
import com.yixu.Config.ConfigManager;
import com.yixu.Config.CookingConfig.CookingGUIConfig;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.CookingGUI.CookingGUIHolder;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Ingredient.RecipeIngredientModel;
import com.yixu.Util.Item.IngredientItemDisplayer;
import com.yixu.Util.Item.RecipeBookNameProvider;
import com.yixu.Util.Message.MessageUtil;
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

import java.util.List;
import java.util.Set;

public class ClickCookingGUIEvent implements Listener {

    private final CookingSessionManager cookingSessionManager;

    private final int bookSlot = CookingGUIConfig.getButtonSlot("Book");
    private final int closeSlot = CookingGUIConfig.getButtonSlot("Close");
    private final int startSlot = CookingGUIConfig.getButtonSlot("Start");

    private static final Set<InventoryAction> VALID_ACTIONS = Set.of(
            InventoryAction.PLACE_ALL,
            InventoryAction.PICKUP_ALL
    );

    public ClickCookingGUIEvent(CookingSessionManager cookingSessionManager) {
        this.cookingSessionManager = cookingSessionManager;
    }

    @EventHandler
    public void onClickCookingGUI(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof CookingGUIHolder)) {
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
        if (slot != bookSlot && slot != closeSlot && slot != startSlot) {
            event.setCancelled(true);
            return;
        }

        RecipeBookNameProvider recipeBookNameProvider = new RecipeBookNameProvider();

        if (slot == bookSlot) {
            displayRecipeMaterial(event, player, recipeBookNameProvider);
            return;
        }

        if (slot == closeSlot) {
            event.setCancelled(true);
            event.getInventory().close();
            return;
        }

        if (slot == startSlot) {
            ItemStack recipeBook = event.getInventory().getItem(bookSlot);

            if (recipeBook == null || recipeBook.getType() == Material.AIR) {
                MessageUtil.sendMessage(player, "cooking.invalid_book");
                event.setCancelled(true);
                return;
            }

            String recipeName = recipeBookNameProvider.getRecipeBookName(recipeBook);
            if (recipeName == null) {
                MessageUtil.sendMessage(player, "cooking.invalid_book");
                event.setCancelled(true);
                return;
            }

            CookingSession playerSession = cookingSessionManager.getPlayerSession(player.getUniqueId());
            Location playerSessionLocation = playerSession.getLocation();

            startCooking(player, recipeBook, recipeName, playerSessionLocation, event);
        }
    }

    private void displayRecipeMaterial(InventoryClickEvent event, Player player, RecipeBookNameProvider recipeBookNameProvider) {
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

        String recipeName = recipeBookNameProvider.getRecipeBookName(cursorItem);
        if (recipeName == null) {
            MessageUtil.sendMessage(player, "cooking.invalid_book");
            event.setCancelled(true);
            return;
        }

        RecipeMaterialMapBuilder builder = new RecipeMaterialMapBuilder();
        List<RecipeIngredientModel> ingredients = builder.buildMaterialMap(recipeName);
        if (ingredients == null || ingredients.isEmpty()) {
            MessageUtil.sendMessage(player, "cooking.invalid_material");
            event.setCancelled(true);
            return;
        }

        IngredientItemDisplayer ingredientItemDisplayer = new IngredientItemDisplayer();
        ingredientItemDisplayer.displayItemInGUI(player, ingredients, recipeName, event.getView());
    }

    private void startCooking(Player player, ItemStack recipeBook, String recipeName, Location guiLocation, InventoryClickEvent event) {
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

        event.setCancelled(true);
        event.getInventory().close();
    }
}