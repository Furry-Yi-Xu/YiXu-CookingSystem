package com.yixu.Util.Hologram;

import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import org.bukkit.Location;

public class DecentHologram {

    public String getHologram(Location location){

        int X = (int) Math.round(location.getX());
        int Y = (int) Math.round(location.getX());
        int Z = (int) Math.round(location.getX());

        String hologramName = location.getWorld().getName() + "_" + X + "_" + Y + "_"  + Z;

        if (DHAPI.getHologram(hologramName) == null) {
            createHologram(location, hologramName);
            return hologramName;
        }

        return hologramName;
    }

    public void createHologram(Location location, String hologramName){
        Location offsetLocation = location.clone().add(-0.15, 2, 0.5);
        Hologram hologram = DHAPI.createHologram(hologramName, offsetLocation, false);
        hologram.setAlwaysFacePlayer(true);
        DHAPI.addHologramLine(hologram, "&r");
    }
}
