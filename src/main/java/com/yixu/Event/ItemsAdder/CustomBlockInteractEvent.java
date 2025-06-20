package com.yixu.Event.ItemsAdder;

import com.yixu.GUI.CookingGUI;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.Plugin;

public class CustomBlockInteractEvent implements Listener {

    private final Plugin plugin;

    public CustomBlockInteractEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onCustomBlockInteract(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event) {

        Player player = event.getPlayer();
        Action action = event.getAction();
        String namespacedID = event.getNamespacedID();

        if (!action.isRightClick()) {
            return;
        }

        if (namespacedID.equals("customcrops_earth_3:deepslate_starlight_ore")) {
            CookingGUI cookingGUI = new CookingGUI();
            cookingGUI.openCookingGUI(player);
        }
    }

}
