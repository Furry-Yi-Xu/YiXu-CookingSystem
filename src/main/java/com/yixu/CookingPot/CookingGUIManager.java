package com.yixu.CookingPot;

import com.yixu.Model.CookingPotModel;
import com.yixu.Model.CookingStatusModel;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CookingGUIManager {

    private final Map<Location, CookingStatusModel> cookingGUIMap = new HashMap<>();
    private final Map<UUID, Location> playerOpenLocationMap = new HashMap<>();

    public CookingStatusModel getCookingGUI(Location location) {

        if (!cookingGUIMap.containsKey(location)) {
            cookingGUIMap.put(location, new CookingStatusModel(location));
        }

        return cookingGUIMap.get(location);
    }

    public boolean isUsed(Location location) {

        CookingStatusModel cookingStatusModelStatus = cookingGUIMap.get(location);

        return cookingGUIMap.get(location) != null && cookingStatusModelStatus.isUsed();
    }

    public boolean isWorking(Location location) {
        CookingStatusModel cookingStatusModelStatus = cookingGUIMap.get(location);

        return cookingGUIMap.get(location) != null && cookingStatusModelStatus.isWorking();
    }

    public void setUsed(Location location, boolean isUsed) {
        CookingStatusModel cookingStatusModelStatus = getCookingGUI(location);
        cookingStatusModelStatus.setUsed(isUsed);
    }

    public void setWorking(Location location, boolean isWorking) {
        CookingStatusModel cookingStatusModelStatus = getCookingGUI(location);
        cookingStatusModelStatus.setWorking(isWorking);
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
