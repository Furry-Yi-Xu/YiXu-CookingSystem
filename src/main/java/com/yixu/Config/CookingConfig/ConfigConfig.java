package com.yixu.Config.CookingConfig;

import com.yixu.Config.ConfigManager;

import java.util.List;

public class ConfigConfig {

    private static final String COOKING_TABLE_NAMESPACE_PATH = "cooking_table.";
    private static final String COOKING_TABLE_HOLOGRAM_PATH = "cooking_table.hologram.";

    public static String getCookingTableName() {
        return ConfigManager.getConfig().getConfig().getString(COOKING_TABLE_NAMESPACE_PATH + "namespaceID");
    }

    public static List<Double> getCookingTableHologramOffset() {
        return ConfigManager.getConfig().getConfig().getDoubleList(COOKING_TABLE_HOLOGRAM_PATH + "offset");
    }

    public static List<String> getCookingTableHologramLines() {
        return ConfigManager.getConfig().getConfig().getStringList(COOKING_TABLE_HOLOGRAM_PATH + "lines");
    }

}
