package com.yixu.Config;

import com.yixu.Config.RecipeConfig.BaseConfig;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    private static BaseConfig configConfig;
    private static BaseConfig messagesConfig;
    private static BaseConfig recipe;

    public static void init(Plugin plugin) {
        configConfig = new BaseConfig(plugin, "config.yml");
        messagesConfig = new BaseConfig(plugin, "messages.yml");
        recipe = new BaseConfig(plugin, "recipes.yml");
    }

    public static BaseConfig getMessagesConfig() {
        return messagesConfig;
    }

    public static BaseConfig getRecipeConfig() {
        return recipe;
    }

    public static BaseConfig getConfig() {
        return configConfig;
    }

    public static void reloadAll() {
        configConfig.reload();
        messagesConfig.reload();
        recipe.reload();
    }
}
