package com.yixu.CookingPot;

import com.yixu.Model.CookingPotModel;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;

public class CookingPotManager {

    private final Map<Location, CookingPotModel> cookingPotModelMap = new HashMap<>();

    public CookingPotModel setCookingPotModelMap(Location location, CookingPotModel cookingPotModel) {

        cookingPotModelMap.put(location, cookingPotModel);

        return cookingPotModelMap.get(location);
    }

    public CookingPotModel getCookingPotModelMap(Location location) {
        return cookingPotModelMap.get(location);
    }
}
