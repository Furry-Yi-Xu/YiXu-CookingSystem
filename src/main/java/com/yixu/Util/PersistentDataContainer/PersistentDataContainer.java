package com.yixu.Util.PersistentDataContainer;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public class PersistentDataContainer {

    public static NamespacedKey createNamespacedKey(JavaPlugin plugin, String key) {
        return new NamespacedKey(plugin, key);
    }

}
