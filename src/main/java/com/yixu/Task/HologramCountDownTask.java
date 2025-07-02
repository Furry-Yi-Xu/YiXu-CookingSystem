package com.yixu.Task;

import com.yixu.Config.CookingConfig.PotConfig;
import com.yixu.Cooking.CookingSessionManager;
import com.yixu.Model.Cooking.CookingState;
import com.yixu.Processor.IngredientInputHologramProcessor;
import com.yixu.Task.AbstractTask.CookingTask;
import com.yixu.Util.Hologram.DecentHologram;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.List;
import java.util.UUID;

public class HologramCountDownTask  extends CookingTask {

    private Hologram hologram;
    private CookingSessionManager cookingSessionManager;
    private IngredientInputHologramProcessor ingredientInputHologramProcessor;

    public HologramCountDownTask(
            Location location,
            int duration,
            Hologram hologram,
            CookingSessionManager cookingSessionManager,
            IngredientInputHologramProcessor ingredientInputHologramProcessor
    ) {
        super(location, duration);
        this.hologram = hologram;
        this.cookingSessionManager = cookingSessionManager;
        this.ingredientInputHologramProcessor = ingredientInputHologramProcessor;
    }

    @Override
    public void cookingTask() {

        duration--;

        if (cookingSessionManager.getSession(location).getCookingState() == CookingState.QTE_ACTIVE) {
            Bukkit.broadcastMessage("任务完成了！");
            forceFinished();
            return;
        }

        if (isFinished()) {
            UUID sessionPlayer = cookingSessionManager.getSessionPlayer(location);

            cookingSessionManager.removeSession(location);
            cookingSessionManager.removePlayerSession(sessionPlayer);

            DHAPI.removeHologram(hologram.getName());

            List<Object> hologramLines = PotConfig.getCookingTableHologramLines();

            List<Double> hologramOffset = PotConfig.getCookingTableHologramOffset();

            DecentHologram.getHologram(location, hologramOffset, hologramLines, false);

            return;
        }

        ingredientInputHologramProcessor.runHologramCountDownProcessor(duration, hologram, cookingSessionManager);

    }

}
