package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Util.Hologram.DecentHologram;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;

public class CustomBlockPlaceEvent implements Listener {

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
