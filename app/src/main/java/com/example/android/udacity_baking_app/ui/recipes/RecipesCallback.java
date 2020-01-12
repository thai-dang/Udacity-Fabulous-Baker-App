package com.example.android.udacity_baking_app.ui.recipes;

import com.example.android.udacity_baking_app.base.BaseCallback;
import com.example.android.udacity_baking_app.model.RecipeResponse;

import java.util.List;

public interface RecipesCallback extends BaseCallback {

    void onResponse(List<RecipeResponse> recipeList);

    void onRecipeError();
}
