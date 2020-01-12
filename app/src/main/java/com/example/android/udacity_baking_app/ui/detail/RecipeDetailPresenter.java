package com.example.android.udacity_baking_app.ui.detail;

import com.example.android.udacity_baking_app.base.BasePresenter;

import javax.inject.Inject;

public class RecipeDetailPresenter implements BasePresenter<RecipeDetailView> {

    RecipeDetailView view;
    RecipeDetailInteractor interactor;

    @Inject
    public RecipeDetailPresenter(RecipeDetailView view, RecipeDetailInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    @Override
    public void setView(RecipeDetailView view) {
        if (view == null) throw new IllegalArgumentException("You can't set a null view");

        this.view = view;
    }

    @Override
    public void detachView() {
        view = null;
    }

    public void addRecipeIngredients(String recipeName, String quantityMeasure) {
        interactor.insertIngredient(recipeName, quantityMeasure);
    }
}
