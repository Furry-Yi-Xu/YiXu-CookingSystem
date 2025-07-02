package com.yixu.Model.Cooking;

import com.yixu.Processor.IngredientInputProcessor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CookingSession {

    private final Location location;
    private UUID boundPlayer;
    private CookingState cookingState;

    private IngredientInputProcessor ingredientInputProcessor;
    private String recipeName;;

    public CookingSession(Location location, CookingState cookingState, Player player) {
        this.location = location;
        this.cookingState = cookingState;
        this.boundPlayer = player.getUniqueId();
    }

    public Location getLocation() {
        return location;
    }

    public UUID getBoundPlayer() {
        return boundPlayer;
    }

    public void setBoundPlayer(UUID boundPlayer) {
        this.boundPlayer = boundPlayer;
    }

    public CookingState getCookingState() {
        return cookingState;
    }

    public void setCookingState(CookingState cookingState) {
        this.cookingState = cookingState;
    }

    public IngredientInputProcessor getIngredientInputProcessor() {
        return ingredientInputProcessor;
    }

    public void setIngredientInputProcessor(IngredientInputProcessor ingredientInputProcessor) {
        this.ingredientInputProcessor = ingredientInputProcessor;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }
}
