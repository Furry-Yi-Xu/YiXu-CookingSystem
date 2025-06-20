package com.yixu.Util.Recipe;

import com.yixu.Config.RecipeConfig.RecipeConfig;
import com.yixu.Model.RecipeIngredient;

import java.util.ArrayList;
import java.util.List;

public class RecipeMaterialMapBuilder {

    public List<RecipeIngredient> buildMaterialMap(String recipeName) {

        List<RecipeIngredient> ingredientList = new ArrayList<>();

        RecipeConfig recipeConfig = new RecipeConfig(recipeName);

        for (int i = 0; i < 4; i++) {

            String type = recipeConfig.getRecipeType(i + 1);
            String material = recipeConfig.getRecipeMaterial(i + 1);
            int amount = recipeConfig.getRecipeAmount(i + 1);

            if (type == null) {
                continue;
            }

            ingredientList.add(new RecipeIngredient(type, material, amount));
        }

        return ingredientList;
    }
}
