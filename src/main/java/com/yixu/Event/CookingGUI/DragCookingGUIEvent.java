package com.yixu.Event.CookingGUI;

import com.yixu.Config.ConfigManager;
import com.yixu.Util.Message.MessageUtil;
import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.plugin.Plugin;

public class DragCookingGUIEvent implements Listener {

    private final Plugin plugin;

    public DragCookingGUIEvent(Plugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDragCookingGUI(InventoryDragEvent event) {

        Component cookingGUITitle = MessageUtil.formatMessage("cooking.cooking_title");

        if (!event.getView().title().equals(cookingGUITitle)) {
            return;
        }

        event.setCancelled(true);

    }
}
