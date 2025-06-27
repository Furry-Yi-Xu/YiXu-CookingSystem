package com.yixu.Processor;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Model.RecipeIngredientModel;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Item.ItemStackResolver;
import com.yixu.Util.Language.ItemNameTranslator;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static net.kyori.adventure.text.Component.text;

public class MaterialInputProcessor {

    protected static Player player;
    protected static Location location;
    protected static String recipeName;
    protected static List<RecipeIngredientModel> recipeIngredient;
    protected int putIngredientDuration;

    protected static final List<Double> hologramOffset = PotConfig.getCookingIngredientHologramOffset();
    protected static final List<String> hologramLines = PotConfig.getCookingIngredientHologramLines();

    public MaterialInputProcessor(Player player, Location location, String recipeName, List<RecipeIngredientModel> recipeIngredient, int putIngredientDuration) {
        this.player = player;
        this.location = location;
        this.recipeName = recipeName;
        this.recipeIngredient = recipeIngredient;
        this.putIngredientDuration = putIngredientDuration;
    }

    public boolean displayPutCookingIngredient() {

        List<RecipeIngredientModel> shuffledIngredients = shuffleIngredients(recipeIngredient);
        String hologramName = DecentHologram.getHologram(location);

        DHAPI.removeHologram(hologramName);

        RecipeIngredientModel ingredients = shuffledIngredients.get(0);
        showCurrentPutIngredients(ingredients);


        return false;
    }

    public void showCurrentPutIngredients(RecipeIngredientModel ingredients) {

        String ingredientsType = ingredients.getType();
        String ingredientsMaterial = ingredients.getMaterial();
        int ingredientsAmount = ingredients.getAmount();

        List<Object> displayLines = new ArrayList<>();

        ItemStack displayItemstack = ItemStackResolver.getItemStack(ingredientsType, ingredientsMaterial);

        Component.translatable(displayItemstack.translationKey());


        for (int i = 0; i < hologramLines.size(); i++) {

            String lineValue = hologramLines.get(i);

            if (lineValue.contains("%material_itemstack%")) {
                displayLines.add(displayItemstack);
            }
            else if (lineValue.contains("%material_name%") || lineValue.contains("%material_amount%")) {

                String itemName = ItemStackResolver.getItemDisplayName(ingredientsType, displayItemstack);

                String replaced = lineValue.
                        replace("%material_name%", itemName).
                        replace("%material_amount%", String.valueOf(ingredientsAmount));

                displayLines.add(replaced);
            }
            else {
                displayLines.add(lineValue);
            }
        }

        Hologram hologram = DecentHologram.getHologram(location, hologramOffset, displayLines, false);
    }

    public List<RecipeIngredientModel> shuffleIngredients(List<RecipeIngredientModel> recipeIngredient) {
        Collections.shuffle(recipeIngredient);
        return recipeIngredient;
    }
}
