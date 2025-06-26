package com.yixu.Config.CookingConfig;

import com.yixu.Config.ConfigManager;

import java.util.List;

public class PotConfig {

    private static final String COOKING_TABLE_NAMESPACE_PATH = "cooking_table.";

    public static String getCookingTableName() {
        return ConfigManager.getPotConfig().getConfig().getString(COOKING_TABLE_NAMESPACE_PATH + "material");
    }

    public static List<Double> getCookingTableHologramOffset() {
        return ConfigManager.getPotConfig().getConfig().getDoubleList(COOKING_TABLE_NAMESPACE_PATH + "hologram.offset");
    }

    public static List<String> getCookingTableHologramLines() {
        return ConfigManager.getPotConfig().getConfig().getStringList(COOKING_TABLE_NAMESPACE_PATH + "hologram.lines");
    }

}
