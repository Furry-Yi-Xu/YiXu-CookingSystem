package com.yixu.Config;

import com.yixu.Config.CookingConfig.BaseConfig;
import org.bukkit.plugin.Plugin;

public class ConfigManager {

    private static BaseConfig configConfig;
    private static BaseConfig messagesConfig;
    private static BaseConfig recipeConfig;
    private static BaseConfig guiConfig;
    private static BaseConfig soundsConfig;

    public static void init(Plugin plugin) {
        configConfig = new BaseConfig(plugin, "config.yml");
        messagesConfig = new BaseConfig(plugin, "messages.yml");
        recipeConfig = new BaseConfig(plugin, "recipes.yml");
        guiConfig = new BaseConfig(plugin, "gui.yml");
        soundsConfig = new BaseConfig(plugin, "sounds.yml");
    }

    public static BaseConfig getMessagesConfig() {
        return messagesConfig;
    }

    public static BaseConfig getRecipeConfig() {
        return recipeConfig;
    }

    public static BaseConfig getConfig() {
        return configConfig;
    }

    public static BaseConfig getGuiConfig() {
        return guiConfig;
    }

    public static BaseConfig getSoundsConfig() {
        return soundsConfig;
    }

    public static void reloadAll() {
        configConfig.reload();
        messagesConfig.reload();
        recipeConfig.reload();
        guiConfig.reload();
        soundsConfig.reload();
    }
}
