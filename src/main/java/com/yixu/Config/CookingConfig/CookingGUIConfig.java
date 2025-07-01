package com.yixu.Config.CookingConfig;

import com.yixu.Config.ConfigManager;
import org.bukkit.configuration.ConfigurationSection;

import java.util.List;
import java.util.Set;

public class CookingGUIConfig {

    private static final String GUI_PATH = "gui.";
    private static final String DISPLAY_PATH = "gui.icons.";

    public static String getTitle() {
        return ConfigManager.
                getGuiConfig().
                getConfig().
                getString(GUI_PATH + "title");
    }
    
    public static Set<String> getIcons() {

        ConfigurationSection guiSection = ConfigManager.getGuiConfig().getConfig().getConfigurationSection("gui");

        ConfigurationSection iconsSection = guiSection.getConfigurationSection("icons");

        return iconsSection.getKeys(false);
    }

    public static String getMaterial(String key) {
        return ConfigManager.
                getGuiConfig().
                getConfig().
                getString(DISPLAY_PATH + key + ".display.material");
    }

    public static int getModelData(String key) {
        return ConfigManager.
                getGuiConfig().
                getConfig().
                getInt(DISPLAY_PATH + key + ".display.data");
    }

    public static String getName(String key) {
        return ConfigManager.
                getGuiConfig().
                getConfig().
                getString(DISPLAY_PATH + key + ".display.name");
    }

    public static List<String> getLore(String key) {
        return ConfigManager.
                getGuiConfig().
                getConfig().
                getStringList(DISPLAY_PATH + key + ".display.lore");
    }

    public static int getButtonSlot(String key) {
        return ConfigManager.
                getGuiConfig().
                getConfig().
                getInt(DISPLAY_PATH + key + ".display.slot");
    }

}
