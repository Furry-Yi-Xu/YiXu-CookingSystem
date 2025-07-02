package com.yixu.Processor;

import com.yixu.Builder.Recipe.RecipeMaterialMapBuilder;
import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Config.CookingConfig.RecipeConfig;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.CookingSystem;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Cooking.CookingState;
import com.yixu.Model.Ingredient.RecipeIngredientModel;
import com.yixu.Scheduler.CookingTaskSyncScheduler;
import com.yixu.Task.HologramCountDownTask;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Item.ItemStackResolver;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IngredientInputProcessor {

    protected final String recipeName;
    protected final RecipeConfig recipeConfig;
    protected int currentPutItemStep;
    protected final List<RecipeIngredientModel> recipeIngredientModelList;

    protected static final List<Double> hologramOffset = PotConfig.getCookingIngredientHologramOffset();
    protected static final List<String> hologramLines = PotConfig.getCookingIngredientHologramLines();

    protected String hologramName;
    protected Hologram hologram;

    protected final CookingSessionManager cookingSessionManager;
    protected final CookingSession cookingSession;

    public IngredientInputProcessor(Location location, CookingSessionManager cookingSessionManager) {
        this.cookingSessionManager = cookingSessionManager;

        this.cookingSession = cookingSessionManager.getSession(location);
        this.recipeName = cookingSession.getRecipeName();
        this.recipeConfig = new RecipeConfig(recipeName);

        List<RecipeIngredientModel> ingredientModelList = new RecipeMaterialMapBuilder().buildMaterialMap(recipeName);
        this.recipeIngredientModelList = shuffleIngredients(ingredientModelList);

        hologramName = DecentHologram.getHologram(location);
        DHAPI.removeHologram(hologramName);

        hologram = DecentHologram.getHologram(location, hologramOffset, new ArrayList<>(), false);
        hologramName = hologram.getName();

        for (int i = 0; i < hologramLines.size(); i++) {
            DHAPI.addHologramLine(hologram, "");
        }

        CookingTaskSyncScheduler cookingTaskSyncScheduler = CookingSystem.getInstance().getCookingTaskSyncScheduler();

        HologramCountDownTask hologramCountDownTask = new HologramCountDownTask(
                cookingSession.getLocation(),
                recipeConfig.getRecipeIngredientTime(),
                hologram,
                cookingSessionManager,
                new IngredientInputHologramProcessor()
        );

        cookingTaskSyncScheduler.addTask(cookingSession.getLocation(), hologramCountDownTask);

        cookingSession.setHologramCountDownTask(hologramCountDownTask);
    }

    public void displayPutCookingIngredient() {

        RecipeIngredientModel ingredient = recipeIngredientModelList.get(currentPutItemStep);

        showCurrentPutIngredients(ingredient);
    }

    public void showCurrentPutIngredients(RecipeIngredientModel ingredient) {

        String type = ingredient.getType();
        String material = ingredient.getMaterial();
        int amount = ingredient.getAmount();

        ItemStack displayItemStack = ItemStackResolver.getItemStack(type, material);

        String displayName = ItemStackResolver.getItemDisplayName(type, displayItemStack);

        for (int i = 0; i < hologramLines.size(); i++) {

            String value = hologramLines.get(i);

            if (value.contains("%material_itemstack%")) {
                DHAPI.setHologramLine(hologram, i, displayItemStack);
                continue;
            }

            if (value.contains("%material_name%") || value.contains("%material_amount%")) {
                String processed = value
                        .replace("%material_name%", displayName)
                        .replace("%material_amount%", String.valueOf(amount));
                DHAPI.setHologramLine(hologram, i, processed);
                continue;
            }

            DHAPI.setHologramLine(hologram, i, value);
        }
    }

    public List<RecipeIngredientModel> shuffleIngredients(List<RecipeIngredientModel> recipeIngredient) {
        Collections.shuffle(recipeIngredient);
        return recipeIngredient;
    }

    public void matchedIngredientMaterial(Player player) {
        ItemStack itemInHandMaterial = player.getInventory().getItemInMainHand();

        if (itemInHandMaterial.getType() == Material.AIR) {
            player.sendMessage("&c这不是正确的食材！");
            return;
        }

        RecipeIngredientModel matchedIngredient = recipeIngredientModelList.get(currentPutItemStep);

        String matchedType = matchedIngredient.getType();
        String matchedMaterial = matchedIngredient.getMaterial();
        int matchedAmount = matchedIngredient.getAmount();

        int itemInHandAmount = itemInHandMaterial.getAmount();

        boolean isMatched = false;

        switch (matchedType) {
            case "itemsadder":
                String namespaceID = ItemStackResolver.getIAItemNamespaceID(itemInHandMaterial);
                if (namespaceID.equalsIgnoreCase(matchedMaterial)) {
                    isMatched = true;
                }
                break;
            case "minecraft":
                Material matchedMaterialType = Material.valueOf(matchedMaterial.toUpperCase());
                if (itemInHandMaterial.getType() == matchedMaterialType) {
                    isMatched = true;
                }
                break;
        }

        if (!isMatched) {
            player.sendMessage("&c这不是正确的食材！");
            return;
        }

        if (itemInHandAmount < matchedAmount) {
            player.sendMessage("&c食材数量不足，需要: " + matchedAmount);
            return;
        }

        itemInHandMaterial.setAmount(itemInHandAmount - matchedAmount);

        currentPutItemStep++;

        cookingSession.getHologramCountDownTask().setDuration(recipeConfig.getRecipeIngredientTime());

        if (currentPutItemStep >= recipeIngredientModelList.size()) {
            Bukkit.broadcastMessage("§a成功放入所有的烹饪物品！");
            cookingSession.setCookingState(CookingState.QTE_ACTIVE);
            return;
        }

        displayPutCookingIngredient();

        player.sendMessage("&a成功投入食材！");
    }

}
