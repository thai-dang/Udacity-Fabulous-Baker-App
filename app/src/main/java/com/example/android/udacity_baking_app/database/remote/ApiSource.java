package com.example.android.udacity_baking_app.database.remote;

import com.example.android.udacity_baking_app.model.RecipeResponse;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface ApiSource {

    @GET("topher/2017/May/59121517_baking/baking.json")
    Single<List<RecipeResponse>> getRecipes();
}
