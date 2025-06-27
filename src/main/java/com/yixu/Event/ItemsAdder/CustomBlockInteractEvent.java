package com.yixu.Event.ItemsAdder;

import com.yixu.Builder.Recipe.RecipeMaterialMapBuilder;
import com.yixu.Config.CookingConfig.ConfigConfig;
import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Config.CookingConfig.RecipeConfig;
import com.yixu.GUI.CookingGUI.CookingGUI;
import com.yixu.GUI.CookingGUIManager;
import com.yixu.Processor.MaterialInputProcessor;
import com.yixu.Util.Hologram.DecentHologram;
import com.yixu.Util.Message.MessageUtil;
import com.yixu.Util.Animation.CookingFireAnimation;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
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

    public CustomBlockInteractEvent(Plugin plugin, CookingGUIManager cookingGUIManager) {
        this.plugin = plugin;
        this.cookingGUIManager = cookingGUIManager;
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
            MessageUtil.sendMessage(player, "cooking.cooking_started");
            return;
        }

        MaterialInputProcessor materialInputProcessor = new MaterialInputProcessor(
                player,
                location,
                "金属药剂",
                new RecipeMaterialMapBuilder().buildMaterialMap("金属药剂"),
                new RecipeConfig("金属药剂").getRecipeCookingTime()
        );

        materialInputProcessor.displayPutCookingIngredient();

        if (true) {
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
