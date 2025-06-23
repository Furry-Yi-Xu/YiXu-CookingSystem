package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.ConfigConfig;
import com.yixu.GUI.CookingGUI.CookingGUI;
import com.yixu.GUI.CookingGUIManager;
import com.yixu.Util.Message.MessageUtil;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.Plugin;

public class CustomBlockInteractEvent implements Listener {

    private final Plugin plugin;
    private final CookingGUIManager cookingGUIManager;

    public CustomBlockInteractEvent(Plugin plugin, CookingGUIManager cookingGUIManager) {
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
    }

    @EventHandler
    public void onCustomBlockInteract(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event) {


        Action action = event.getAction();

        if (!action.isRightClick()) {
            return;
        }

        Player player = event.getPlayer();
        String namespacedID = event.getNamespacedID();
        Location location = event.getBlockClicked().getLocation();

        if (cookingGUIManager.isUsed(location)) {
            MessageUtil.sendMessage(player, "cooking.cooking_used");
            return;
        }

        if (cookingGUIManager.isWorking(location)) {
            MessageUtil.sendMessage(player, "cooking.cooking_started");
            return;
        }

        if (namespacedID.equals(ConfigConfig.getCookingTableName())) {
            CookingGUI cookingGUI = new CookingGUI();
            cookingGUI.openCookingGUI(player);

            cookingGUIManager.setUsed(location, true);
            cookingGUIManager.setCookingTableLocation(player.getUniqueId(), location);
        }
    }

}
