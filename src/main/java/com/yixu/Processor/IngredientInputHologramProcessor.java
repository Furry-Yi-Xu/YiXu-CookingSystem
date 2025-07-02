package com.yixu.Processor;

import com.yixu.Cooking.CookingSessionManager;
import eu.decentsoftware.holograms.api.DHAPI;
import eu.decentsoftware.holograms.api.holograms.Hologram;
import eu.decentsoftware.holograms.api.holograms.HologramLine;
import java.util.List;

public class IngredientInputHologramProcessor {

    protected int duration;

    protected String recordContent;
    protected int recordLine;

    public void runHologramCountDownProcessor(int duration, Hologram hologram, CookingSessionManager cookingSessionManager) {

        this.duration = duration;

        if (recordContent == null) {
            List<HologramLine> hologramLines = hologram.getPage(0).getLines();
            for (int i = 0; i < hologramLines.size(); i++) {
                String value = hologramLines.get(i).getContent();
                if (value.contains("%remaining_time%")) {
                    this.recordContent = value;
                    this.recordLine = i;
                    String countdownText = value.replace("%remaining_time%", String.valueOf(duration));
                    DHAPI.setHologramLine(hologram, i, countdownText);
                    break;
                }
            }
        }

        String countdownText = recordContent.replace("%remaining_time%", String.valueOf(duration));
        DHAPI.setHologramLine(hologram, recordLine, countdownText);

        DHAPI.updateHologram(hologram.getName());

    }

}
