package com.yixu.Model.Cooking;

import com.yixu.Cooking.CookingSessionManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CookingSession {

    private final Location location;
    private UUID boundPlayer;
    private CookingState cookingState;

    public CookingSession(Location location, CookingState cookingState, Player player) {
        this.location = location;
        this.cookingState = cookingState;
        this.boundPlayer = player.getUniqueId();
    }

    public Location getLocation() {
        return location;
    }

    public UUID getBoundPlayer() {
        return boundPlayer;
    }

    public void setBoundPlayer(UUID boundPlayer) {
        this.boundPlayer = boundPlayer;
    }

    public CookingState getCookingState() {
        return cookingState;
    }

    public void setCookingState(CookingState cookingState) {
        this.cookingState = cookingState;
    }
}
