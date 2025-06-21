package com.yixu.GUI;

import com.yixu.GUI.CookingGUI.Cooking;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CookingGUIManager {

    private final Map<Location, Cooking> cookingGUIMap = new HashMap<>();

    private final Map<UUID, Location> playerOpenLocationMap = new HashMap<>();

    public Cooking getCookingGUI(Location location) {

        if (!cookingGUIMap.containsKey(location)) {
            cookingGUIMap.put(location, new Cooking(location));
        }

        return cookingGUIMap.get(location);
    }

    public boolean isUsed(Location location) {

        Cooking cookingStatus = cookingGUIMap.get(location);

        return cookingGUIMap.get(location) != null && cookingStatus.isUsed();
    }

    public boolean isWorking(Location location) {
        Cooking cookingStatus = cookingGUIMap.get(location);

        return cookingGUIMap.get(location) != null && cookingStatus.isWorking();
    }

    public void setUsed(Location location, boolean isUsed) {
        Cooking cookingStatus = getCookingGUI(location);
        cookingStatus.setUsed(isUsed);
    }

    public void setWorking(Location location, boolean isWorking) {
        Cooking cookingStatus = getCookingGUI(location);
        cookingStatus.setWorking(isWorking);
    }

    public void removeCookingStatus(Location location) {
        cookingGUIMap.remove(location);
    }

    public void setCookingTableLocation(UUID uuid, Location location) {
        playerOpenLocationMap.put(uuid, location);
    }

    public Location getCookingTableLocation(UUID uuid) {

        return playerOpenLocationMap.get(uuid);
    }

}
