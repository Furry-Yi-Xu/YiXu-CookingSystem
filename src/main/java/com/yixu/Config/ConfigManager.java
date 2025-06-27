package com.yixu.Config;

import com.yixu.Config.CookingConfig.BaseConfig;
import org.bukkit.plugin.Plugin;

import java.io.FileNotFoundException;

public class ConfigManager {

    private static BaseConfig configConfig;
    private static BaseConfig messagesConfig;
    private static BaseConfig recipeConfig;
    private static BaseConfig guiConfig;
    private static BaseConfig soundsConfig;
    private static BaseConfig potConfig;

    private static BaseConfig itemJson;

    public static void init(Plugin plugin) throws FileNotFoundException {
        configConfig = new BaseConfig(plugin, "config.yml");
        messagesConfig = new BaseConfig(plugin, "messages.yml");
        recipeConfig = new BaseConfig(plugin, "recipes.yml");
        guiConfig = new BaseConfig(plugin, "gui.yml");
        soundsConfig = new BaseConfig(plugin, "sounds.yml");
        potConfig = new BaseConfig(plugin, "pot.yml");
        itemJson = new BaseConfig(plugin, "Translations/Items_zh_CN.json");
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

    public static BaseConfig getPotConfig() {
        return potConfig;
    }

    public static BaseConfig getItemJson() {
        return itemJson;
    }

    public static void reloadAll() {
        configConfig.reload();
        messagesConfig.reload();
        recipeConfig.reload();
        guiConfig.reload();
        soundsConfig.reload();
        potConfig.reload();
        itemJson.reload();
    }
}
