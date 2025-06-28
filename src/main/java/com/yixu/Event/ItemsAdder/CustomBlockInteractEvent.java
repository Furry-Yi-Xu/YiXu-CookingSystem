package com.yixu.Event.ItemsAdder;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.CookingPot.CookingGUI.CookingGUI;
import com.yixu.CookingPot.CookingGUIManager;
import com.yixu.CookingPot.CookingPotManager;
import com.yixu.Model.CookingPotModel;
import com.yixu.Processor.MaterialInputProcessor;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Message.MessageUtil;
import eu.decentsoftware.holograms.api.DHAPI;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.plugin.Plugin;

import java.util.List;

public class CustomBlockInteractEvent implements Listener {

    private final Plugin plugin;
    private final CookingGUIManager cookingGUIManager;
    private final CookingPotManager cookingPotManager;

    public CustomBlockInteractEvent(Plugin plugin, CookingGUIManager cookingGUIManager, CookingPotManager cookingPotManager) {
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
        this.cookingPotManager = cookingPotManager;
    }

    @EventHandler
    public void onCustomBlockInteract(dev.lone.itemsadder.api.Events.CustomBlockInteractEvent event) throws InterruptedException {


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

            CookingPotModel cookingPotModel = cookingPotManager.getCookingPotModelMap(location);

            if (cookingPotModel.getBoundPlayer().equals(player.getUniqueId())) {

                MaterialInputProcessor inputProcessor = cookingPotModel.getInputProcessor();

                inputProcessor.matchedIngredientMaterial(player);

                return;
            }

            MessageUtil.sendMessage(player, "cooking.cooking_started");
            return;
        }

        if (namespacedID.equals(PotConfig.getCookingTableName())) {

            String hologram = DecentHologram.getHologram(location);

            if (DHAPI.getHologram(hologram) == null) {
                List<Object> hologramLines = PotConfig.getCookingTableHologramLines();

                List<Double> hologramOffset = PotConfig.getCookingTableHologramOffset();

                DecentHologram.getHologram(location, hologramOffset, hologramLines, false);
            }

            CookingGUI cookingGUI = new CookingGUI();
            cookingGUI.openCookingGUI(player);

            cookingGUIManager.setUsed(location, true);
            cookingGUIManager.setCookingTableLocation(player.getUniqueId(), location);

        }
    }

}
