package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.ConfigConfig;
import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.GUI.CookingGUIManager;
import com.yixu.Util.Hologram.DecentHologram;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class CustomBlockPlaceEvent implements Listener {

    private final Plugin plugin;

    public CustomBlockPlaceEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCustomBlockPlaceEvent(dev.lone.itemsadder.api.Events.CustomBlockPlaceEvent event) {

        String namespacedID = event.getNamespacedID();

        if (namespacedID.equals(PotConfig.getCookingTableName())) {

            Location location = event.getBlock().getLocation();

            List<Object> hologramLines = PotConfig.getCookingTableHologramLines();

            List<Double> hologramOffset = PotConfig.getCookingTableHologramOffset();

            DecentHologram.getHologram(location, hologramOffset, hologramLines, false);

        }
    }
}
