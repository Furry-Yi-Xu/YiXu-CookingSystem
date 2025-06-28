package com.yixu.Model;

import com.yixu.Processor.MaterialInputProcessor;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class CookingPotModel {

    private Location location;
    private UUID boundPlayer;
    private String recipeName;
    private int currentPutItemStep = 0;
    private List<RecipeIngredientModel> shuffledIngredients;
    private MaterialInputProcessor inputProcessor;

    public CookingPotModel(Location location, UUID boundPlayer, String recipeName) {
        this.location = location;
        this.boundPlayer = boundPlayer;
        this.recipeName = recipeName;
    }

    public CookingPotModel(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public UUID getBoundPlayer() {
        return boundPlayer;
    }

    public void setBoundPlayer(UUID boundPlayer) {
        this.boundPlayer = boundPlayer;
    }

    public String getRecipeName() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName = recipeName;
    }

    public int getCurrentPutItemStep() {
        return currentPutItemStep;
    }

    public void setCurrentPutItemStep(int currentPutItemStep) {
        this.currentPutItemStep = currentPutItemStep;
    }

    public List<RecipeIngredientModel> getShuffledIngredients() {
        return shuffledIngredients;
    }

    public void setShuffledIngredients(List<RecipeIngredientModel> shuffledIngredients) {
        this.shuffledIngredients = shuffledIngredients;
    }

    public MaterialInputProcessor getInputProcessor() {
        return inputProcessor;
    }

    public void setInputProcessor(MaterialInputProcessor inputProcessor) {
        this.inputProcessor = inputProcessor;
    }
}
