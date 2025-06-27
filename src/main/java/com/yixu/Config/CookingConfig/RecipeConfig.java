package com.yixu.Config.CookingConfig;

import com.yixu.Config.ConfigManager;

public class RecipeConfig {

    private final String path;

    public RecipeConfig(String recipeName) {
        this.path = recipeName + ".";
    }

    public String getRecipeType(int index) {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getString(path + "ingredient_" + index + ".type");
    }

    public String getRecipeMaterial(int index) {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getString(path + "ingredient_" + index + ".material");
    }

    public int getRecipeAmount(int index) {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getInt(path + "ingredient_" + index + ".amount", 1);
    }

    public String getRecipeBookName() {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getString(path + "name");
    }

    public String getRecipeBookStamina() {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getString(path + "stamina");
    }

    public String getRecipeResultMaterial() {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getString(path + "material");
    }

    public String getRecipeResultAmount() {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getString(path + "amount");
    }

    public int getRecipeCookingTime() {
        return ConfigManager.getRecipeConfig()
                .getConfig()
                .getInt(path + "cooking.cooking_time");
    }

}
