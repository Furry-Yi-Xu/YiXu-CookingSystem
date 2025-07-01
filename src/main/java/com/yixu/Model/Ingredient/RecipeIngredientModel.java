package com.yixu.Model.Ingredient;

public class RecipeIngredientModel {
    private final String type;
    private final String material;
    private final int amount;

    public RecipeIngredientModel(String type, String material, int amount) {
        this.type = type;
        this.material = material;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public String getMaterial() {
        return material;
    }

    public int getAmount() {
        return amount;
    }
}
