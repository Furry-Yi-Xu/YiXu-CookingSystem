package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Cooking.CookingStateMachine;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Cooking.CookingState;
import com.yixu.Processor.IngredientInputProcessor;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Message.MessageUtil;

import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.UUID;

public class CustomBlockInteractEvent implements Listener {

    private final CookingSessionManager cookingSessionManager;

    public CustomBlockInteractEvent(CookingSessionManager cookingSessionManager) {
        this.cookingSessionManager = cookingSessionManager;
    }

    @EventHandler
    public void onCustomBlockInteractEvent(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event) {

        if (!event.getAction().isRightClick()) {
            return;
        }

        String namespacedID = event.getNamespacedID();

        if (!namespacedID.equals(PotConfig.getCookingTableName())) {
            return;
        }

        Player player = event.getPlayer();
        UUID playerUUID = player.getUniqueId();

        Location location = event.getBlockClicked().getLocation();
        String hologram = DecentHologram.getHologram(location);

        if (DHAPI.getHologram(hologram) == null) {

            List<Object> hologramLines = PotConfig.getCookingTableHologramLines();

            List<Double> hologramOffset = PotConfig.getCookingTableHologramOffset();

            DecentHologram.getHologram(location, hologramOffset, hologramLines, false);
        }

        CookingSession cookingSession = cookingSessionManager.getSession(location);
        if (cookingSession == null) {
            cookingSession = new CookingSession(location, CookingState.WAITING_FOR_RECIPE_BOOK, player);
            cookingSessionManager.addSession(location, cookingSession);
        }

        if (cookingSession.getBoundPlayer() == null) {
            cookingSession.setBoundPlayer(playerUUID);
        }

        if (cookingSession.getBoundPlayer() != playerUUID) {
            MessageUtil.sendMessage(player, "cooking.cooking_used");
            return;
        }

        if (cookingSession.getCookingState() == CookingState.WAITING_FOR_INGREDIENTS) {

            IngredientInputProcessor ingredientInputProcessor = cookingSession.getIngredientInputProcessor();

            if (ingredientInputProcessor != null) {
                ingredientInputProcessor.matchedIngredientMaterial(player);
            }

            event.setCancelled(true);
            return;
        }

        CookingStateMachine stateMachine = new CookingStateMachine();
        stateMachine.handleEvent(location, cookingSessionManager);
    }

}
