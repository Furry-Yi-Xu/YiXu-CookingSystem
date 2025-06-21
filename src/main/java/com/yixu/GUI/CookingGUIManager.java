package com.yixu.GUI;

import com.yixu.Model.CookingModel;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CookingGUIManager {

    private final Map<Location, CookingModel> cookingGUIMap = new HashMap<>();

    private final Map<UUID, Location> playerOpenLocationMap = new HashMap<>();

    public CookingModel getCookingGUI(Location location) {

        if (!cookingGUIMap.containsKey(location)) {
            cookingGUIMap.put(location, new CookingModel(location));
        }

        return cookingGUIMap.get(location);
    }

    public boolean isUsed(Location location) {

        CookingModel cookingModelStatus = cookingGUIMap.get(location);

        return cookingGUIMap.get(location) != null && cookingModelStatus.isUsed();
    }

    public boolean isWorking(Location location) {
        CookingModel cookingModelStatus = cookingGUIMap.get(location);

        return cookingGUIMap.get(location) != null && cookingModelStatus.isWorking();
    }

    public void setUsed(Location location, boolean isUsed) {
        CookingModel cookingModelStatus = getCookingGUI(location);
        cookingModelStatus.setUsed(isUsed);
    }

    public void setWorking(Location location, boolean isWorking) {
        CookingModel cookingModelStatus = getCookingGUI(location);
        cookingModelStatus.setWorking(isWorking);
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
