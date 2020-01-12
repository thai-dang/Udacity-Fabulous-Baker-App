package com.example.android.udacity_baking_app.resource;

import com.example.android.udacity_baking_app.ui.detail.RecipeDetailFragment;
import com.example.android.udacity_baking_app.ui.recipes.RecipeListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuilder {

    @ContributesAndroidInjector
    abstract RecipeListFragment contributeRecipeListFragment();

    @ContributesAndroidInjector
    abstract RecipeDetailFragment contributeRecipeDetailFragment();
}
