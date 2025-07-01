package com.yixu.Builder.Recipe;

import com.yixu.Config.CookingConfig.RecipeConfig;
import com.yixu.Model.Ingredient.RecipeIngredientModel;

import java.util.ArrayList;
import java.util.List;

public class RecipeMaterialMapBuilder {

    public List<RecipeIngredientModel> buildMaterialMap(String recipeName) {

        List<RecipeIngredientModel> ingredientList = new ArrayList<>();

        RecipeConfig recipeConfig = new RecipeConfig(recipeName);

        for (int i = 0; i < 4; i++) {

            String type = recipeConfig.getRecipeType(i + 1);
            String material = recipeConfig.getRecipeMaterial(i + 1);
            int amount = recipeConfig.getRecipeAmount(i + 1);

            if (type == null) {
                continue;
            }

            ingredientList.add(new RecipeIngredientModel(type, material, amount));
        }

        return ingredientList;
    }
}
