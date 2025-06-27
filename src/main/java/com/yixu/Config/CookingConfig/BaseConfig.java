package com.yixu.Config.CookingConfig;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BaseConfig {
    private final Plugin plugin;
    private final String fileName;
    private File file;
    private FileConfiguration config;

    private Map<String, String> jsonConfig = new HashMap<>();

    public BaseConfig(Plugin plugin, String fileName) throws FileNotFoundException {
        this.plugin = plugin;
        this.fileName = fileName;
        loadConfig();
    }

    private void loadConfig() throws FileNotFoundException {
        file = new File(plugin.getDataFolder(), fileName);

        if (!file.exists()) {
            plugin.saveResource(fileName, false);
        }

        if (fileName.endsWith(".yml") || fileName.endsWith(".yaml")) {
            config = YamlConfiguration.loadConfiguration(file);
        }

        if (fileName.endsWith(".json")) {
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            Type type = new TypeToken<Map<String, String>>() {
            }.getType();
            jsonConfig = new Gson().fromJson(reader, type);
        }
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public Map<String, String> getJsonConfig() {
        return Collections.unmodifiableMap(jsonConfig);
    }

    public String getJsonValue(String key) {
        return jsonConfig.getOrDefault(key, key);
    }

}
