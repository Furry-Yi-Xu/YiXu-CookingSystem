package com.yixu.Config.CookingConfig;

import com.yixu.Config.ConfigManager;

import java.util.List;

public class PotConfig {

    private static final String COOKING_TABLE_NAMESPACE_PATH = "cooking_table.";
    private static final String COOKING_INGREDIENT_PATH = "cooking_ingredient.";
    private static final String COOKING_PROCESS_PATH = "cooking_process.";

    public static String getCookingTableName() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getString(COOKING_TABLE_NAMESPACE_PATH + "material");
    }

    public static List<Double> getCookingTableHologramOffset() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getDoubleList(COOKING_TABLE_NAMESPACE_PATH + "hologram.offset");
    }

    public static List getCookingTableHologramLines() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getStringList(COOKING_TABLE_NAMESPACE_PATH + "hologram.lines");
    }

    public static List<Double> getCookingIngredientHologramOffset() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getDoubleList(COOKING_INGREDIENT_PATH + "hologram.offset");
    }

    public static List<String> getCookingIngredientHologramLines() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getStringList(COOKING_INGREDIENT_PATH + "hologram.lines");
    }

    public static List<Double> getCookingProcessHologramOffset() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getDoubleList(COOKING_PROCESS_PATH + "hologram.offset");
    }

    public static List<String> getCookingProcessHologramLines() {
        return ConfigManager.
                getPotConfig().
                getConfig().
                getStringList(COOKING_PROCESS_PATH + "hologram.lines");
    }

}
