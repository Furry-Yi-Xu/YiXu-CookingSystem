package com.yixu.Config.CookingConfig;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class BaseConfig {
    private final Plugin plugin;
    private final String fileName;
    private File file;
    private FileConfiguration config;

    public BaseConfig(Plugin plugin, String fileName) {
        this.plugin = plugin;
        this.fileName = fileName;
        loadConfig();
    }

    private void loadConfig() {
        file = new File(plugin.getDataFolder(), fileName);
        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

}
