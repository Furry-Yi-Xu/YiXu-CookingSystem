package com.yixu.Util.Hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class DecentHologram {

    public static String getHologram(Location location) {

        int X = (int) Math.round(location.getX());
        int Y = (int) Math.round(location.getY());
        int Z = (int) Math.round(location.getZ());

        String hologramName = location.getWorld().getName() + "_" + X + "_" + Y + "_" + Z;

        return hologramName;
    }

    public static Hologram getHologram(Location location, List<Double> hologramOffset, List<Object> hologramLines, boolean isSaveToFile) {

        int X = (int) Math.round(location.getX());
        int Y = (int) Math.round(location.getY());
        int Z = (int) Math.round(location.getZ());

        String hologramName = location.getWorld().getName() + "_" + X + "_" + Y + "_" + Z;

        if (DHAPI.getHologram(hologramName) == null) {
            createHologram(location, hologramName, hologramOffset, hologramLines, isSaveToFile);
        }

        return DHAPI.getHologram(hologramName);
    }

    public static void createHologram(Location location, String hologramName, List<Double> hologramOffset, List<Object> hologramLines, boolean isSaveToFile) {
        Location offsetLocation = location.clone().add(hologramOffset.get(0), hologramOffset.get(1), hologramOffset.get(2));
        Hologram hologram = DHAPI.createHologram(hologramName, offsetLocation, isSaveToFile);
        hologram.setAlwaysFacePlayer(true);
        for (int i = 0; i < hologramLines.size(); i++) {

            Object line = hologramLines.get(i);

            if (line instanceof String text) {
                DHAPI.addHologramLine(hologram, text);
            }

            if (line instanceof ItemStack itemStack) {
                DHAPI.addHologramLine(hologram, itemStack);
            }

        }
    }
}
