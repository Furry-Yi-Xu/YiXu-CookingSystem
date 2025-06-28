package com.yixu.Processor;

import com.yixu.Builder.Recipe.RecipeMaterialMapBuilder;
import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Model.CookingPotModel;
import com.yixu.Model.RecipeIngredientModel;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Item.ItemStackResolver;
import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class MaterialInputProcessor {

    protected final CookingPotModel cookingPotModel;
    protected final Location location;
    protected final UUID boundPlayer;
    protected final String recipeName;
    protected int currentPutItemStep;
    protected final List<RecipeIngredientModel> recipeIngredient;
    protected final List<RecipeIngredientModel> shuffledIngredients;

    protected static final List<Double> hologramOffset = PotConfig.getCookingIngredientHologramOffset();
    protected static final List<String> hologramLines = PotConfig.getCookingIngredientHologramLines();

    public MaterialInputProcessor(CookingPotModel cookingPotModel) {
        this.cookingPotModel = cookingPotModel;
        this.location = cookingPotModel.getLocation();
        this.boundPlayer = cookingPotModel.getBoundPlayer();
        this.recipeName = cookingPotModel.getRecipeName();
        this.currentPutItemStep = cookingPotModel.getCurrentPutItemStep();
        this.recipeIngredient = new RecipeMaterialMapBuilder().buildMaterialMap(recipeName);
        this.shuffledIngredients = shuffleIngredients(recipeIngredient);
    }

    public void displayPutCookingIngredient() {

        cookingPotModel.setShuffledIngredients(shuffledIngredients);

        if (currentPutItemStep >= 4) {
            Bukkit.broadcastMessage("成功放入所有的烹饪物品！");
            return;
        }

        String hologramName = DecentHologram.getHologram(location);
        DHAPI.removeHologram(hologramName);

        RecipeIngredientModel ingredients = shuffledIngredients.get(currentPutItemStep);

        if (ingredients == null) {
            currentPutItemStep++;
            return;
        }

        showCurrentPutIngredients(ingredients);

    }

    public void showCurrentPutIngredients(RecipeIngredientModel ingredients) {

        List<Object> displayLines = new ArrayList<>();

        String ingredientsType = ingredients.getType();
        String ingredientsMaterial = ingredients.getMaterial();
        int ingredientsAmount = ingredients.getAmount();

        ItemStack displayItemstack = ItemStackResolver.getItemStack(ingredientsType, ingredientsMaterial);

        for (String lineValue : hologramLines) {

            if (lineValue.contains("%material_itemstack%")) {
                displayLines.add(displayItemstack);
            } else if (lineValue.contains("%material_name%") || lineValue.contains("%material_amount%")) {

                String itemName = ItemStackResolver.getItemDisplayName(ingredientsType, displayItemstack);

                String replaced = lineValue.
                        replace("%material_name%", itemName).
                        replace("%material_amount%", String.valueOf(ingredientsAmount));

                displayLines.add(replaced);
            } else {
                displayLines.add(lineValue);
            }
        }

        DecentHologram.getHologram(location, hologramOffset, displayLines, false);
    }

    public List<RecipeIngredientModel> shuffleIngredients(List<RecipeIngredientModel> recipeIngredient) {
        Collections.shuffle(recipeIngredient);
        return recipeIngredient;
    }

    public void matchedIngredientMaterial(Player player) {
        ItemStack itemInHandMaterial = player.getInventory().getItemInMainHand();

        if (itemInHandMaterial.getType() == Material.AIR) {
            player.sendMessage("&c不支持的食材类型");
            return;
        }

        RecipeIngredientModel matchedIngredient = cookingPotModel.getShuffledIngredients().get(currentPutItemStep);

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

        displayPutCookingIngredient();

        player.sendMessage("&a成功投入食材！");
    }

}
