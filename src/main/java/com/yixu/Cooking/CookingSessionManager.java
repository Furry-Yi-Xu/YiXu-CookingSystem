package com.yixu.Cooking;

import com.yixu.Model.Cooking.CookingSession;
import org.bukkit.Location;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CookingSessionManager {

    private final Map<Location, CookingSession> activeSessions = new HashMap<>();

    private final Map<UUID, CookingSession> playerSession = new HashMap<>();

    public CookingSession getSession(Location location) {
        return activeSessions.get(location);
    }

    public void addSession(Location location, CookingSession cookingSession) {
        activeSessions.put(location, cookingSession);
    }

    public void removeSession(Location location) {
        activeSessions.remove(location);
    }

    public boolean hasSession(Location location) {
        return activeSessions.containsKey(location.toBlockLocation());
    }

    public void clearAllSession() {
        activeSessions.clear();
    }

    public UUID getSessionPlayer(Location location) {
        return activeSessions.get(location).getBoundPlayer();
    }

    public Map<Location, CookingSession> getActiveSessions() {
        return activeSessions;
    }

    public void setPlayerSession(UUID uuid, CookingSession cookingSession) {
        playerSession.put(uuid, cookingSession);
    }

    public CookingSession getPlayerSession(UUID uuid) {
        return playerSession.get(uuid);
    }
}
