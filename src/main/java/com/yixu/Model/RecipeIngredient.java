package com.yixu.Model;

public class RecipeIngredient {
    private final String type;
    private final String material;
    private final int amount;

    public RecipeIngredient(String type, String material, int amount) {
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
