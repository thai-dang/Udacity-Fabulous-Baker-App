package com.example.android.udacity_baking_app.ui.recipes;

import com.example.android.udacity_baking_app.base.MvpView;
import com.example.android.udacity_baking_app.model.RecipeResponse;

import java.util.List;

public interface RecipesMvpView extends MvpView {

    void renderRecipes(List<RecipeResponse> recipesList);
}
