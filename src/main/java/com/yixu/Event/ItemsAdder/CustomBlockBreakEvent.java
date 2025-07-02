package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Cooking.CookingState;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Message.MessageUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
public class CustomBlockBreakEvent implements Listener {

    private final CookingSessionManager cookingSessionManager;

    public CustomBlockBreakEvent(CookingSessionManager cookingSessionManager) {
        this.cookingSessionManager = cookingSessionManager;
    }

    @EventHandler
    public void onCustomBlockInteractEvent(dev.lone.itemsadder.api.Events.CustomBlockBreakEvent event){

        String namespacedID = event.getNamespacedID();

        if (namespacedID.equals(PotConfig.getCookingTableName())) {

            Location location = event.getBlock().getLocation();

            CookingSession cookingSession = cookingSessionManager.getSession(location);

            if (cookingSession.getCookingState() == CookingState.WAITING_FOR_RECIPE_BOOK) {
                MessageUtil.sendMessage(event.getPlayer(), "cooking.cooking_used_break");
                event.setCancelled(true);
                return;
            }

            if (cookingSession.getCookingState() == CookingState.WAITING_FOR_INGREDIENTS) {
                MessageUtil.sendMessage(event.getPlayer(), "cooking.cooking_started_break");
                event.setCancelled(true);
                return;
            }

            String hologramName = DecentHologram.getHologram(location);

            DHAPI.removeHologram(hologramName);

        }
    }
}
