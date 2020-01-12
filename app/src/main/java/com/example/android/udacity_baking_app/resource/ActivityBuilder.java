package com.example.android.udacity_baking_app.resource;

import com.example.android.udacity_baking_app.ui.detail.RecipeDetailActivity;
import com.example.android.udacity_baking_app.ui.recipes.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract MainActivity mainActivity();

    @ContributesAndroidInjector(modules = FragmentBuilder.class)
    abstract RecipeDetailActivity recipeDetailActivity();
}
