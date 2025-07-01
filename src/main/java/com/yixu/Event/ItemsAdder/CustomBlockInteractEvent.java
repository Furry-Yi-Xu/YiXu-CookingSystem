package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Cooking.CookingStateMachine;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Cooking.CookingState;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Message.MessageUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;

import java.util.List;
import java.util.UUID;

public class CustomBlockInteractEvent implements Listener {

    private final CookingSessionManager cookingSessionManager;

    public CustomBlockInteractEvent(CookingSessionManager cookingSessionManager) {
        this.cookingSessionManager = cookingSessionManager;
    }

    @EventHandler
    public void onCustomBlockInteractEvent(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event) {

        Action action = event.getAction();

        if (!action.isRightClick()) {
            return;
        }

        Location location = event.getBlockClicked().getLocation();
        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        if (!cookingSessionManager.hasSession(location)) {
            cookingSessionManager.addSession(location, new CookingSession(location, CookingState.WAITING_FOR_RECIPE_BOOK, player));
        }

        UUID boundPlayer = cookingSessionManager.getSession(location).getBoundPlayer();

        if (boundPlayer == null) {
            cookingSessionManager.getSession(location).setBoundPlayer(playerUUID);
            boundPlayer = cookingSessionManager.getSession(location).getBoundPlayer();
        }

        if (!playerUUID.equals(boundPlayer)) {
            MessageUtil.sendMessage(player, "cooking.cooking_used");
            return;
        }

        String namespacedID = event.getNamespacedID();

        if (namespacedID.equals(PotConfig.getCookingTableName())) {

            String hologram = DecentHologram.getHologram(location);

            if (DHAPI.getHologram(hologram) == null) {

                List<Object> hologramLines = PotConfig.getCookingTableHologramLines();

                List<Double> hologramOffset = PotConfig.getCookingTableHologramOffset();

                DecentHologram.getHologram(location, hologramOffset, hologramLines, false);
            }

            CookingStateMachine cookingStateMachine = new CookingStateMachine();

            cookingStateMachine.handleEvent(location, cookingSessionManager);

        }
    }
}
