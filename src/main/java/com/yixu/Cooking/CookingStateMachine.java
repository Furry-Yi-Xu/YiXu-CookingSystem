package com.yixu.Cooking;

import com.yixu.CookingGUI.CookingGUI;
import com.yixu.CookingSystem;
import com.yixu.Model.Cooking.CookingSession;
import com.yixu.Model.Cooking.CookingState;
import com.yixu.Processor.IngredientInputProcessor;
import com.yixu.Scheduler.CookingTaskSyncScheduler;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class CookingStateMachine {

    public void handleEvent(Location location, CookingSessionManager cookingSessionManager) {

        CookingState cookingState = cookingSessionManager.getSession(location).getCookingState();

        switch (cookingState) {
            case WAITING_FOR_RECIPE_BOOK:
                handleRecipeBook(location, cookingSessionManager);
                break;
            case WAITING_FOR_INGREDIENTS:
                handleIngredient(location, cookingSessionManager);
                break;
        }

    }

    public void handleRecipeBook(Location location, CookingSessionManager cookingSessionManager) {

        UUID playerUUID = cookingSessionManager.getSessionPlayer(location);
        Player player = Bukkit.getPlayer(playerUUID);

        cookingSessionManager.setPlayerSession(playerUUID, cookingSessionManager.getSession(location));

        CookingGUI cookingGUI = new CookingGUI();
        cookingGUI.openCookingGUI(player);

    }

    public void handleIngredient(Location location, CookingSessionManager cookingSessionManager) {

        CookingSession cookingSession = cookingSessionManager.getSession(location);

        IngredientInputProcessor ingredientInputProcessor = new IngredientInputProcessor(location, cookingSessionManager);

        cookingSession.setIngredientInputProcessor(ingredientInputProcessor);

        ingredientInputProcessor.displayPutCookingIngredient();

    }

}
