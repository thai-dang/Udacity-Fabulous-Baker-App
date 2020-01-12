package com.example.android.udacity_baking_app.resource;

import android.app.Application;
import android.content.Context;

import com.example.android.udacity_baking_app.database.remote.ApiSource;
import com.example.android.udacity_baking_app.ui.detail.RecipeDetailFragment;
import com.example.android.udacity_baking_app.ui.detail.RecipeDetailInteractor;
import com.example.android.udacity_baking_app.ui.detail.RecipeDetailPresenter;
import com.example.android.udacity_baking_app.ui.detail.RecipeDetailView;
import com.example.android.udacity_baking_app.ui.recipes.RecipeListFragment;
import com.example.android.udacity_baking_app.ui.recipes.RecipesInteractor;
import com.example.android.udacity_baking_app.ui.recipes.RecipesMvpView;
import com.example.android.udacity_baking_app.ui.recipes.RecipesPresenter;
import com.example.android.udacity_baking_app.utils.ConstantDefs;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    @Provides
    @Singleton
    HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    ApiSource provideApiSource(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ConstantDefs.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiSource.class);
    }

    @Provides
    Context provideContext(Application application) {
        return application.getApplicationContext();
    }

    @Provides
    RecipesMvpView provideRecipesMvpView() {
        return new RecipeListFragment();
    }

    @Provides
    RecipesInteractor provideRecipesInteractor(ApiSource apiSource) {
        return new RecipesInteractor(apiSource);
    }

    @Provides
    RecipesPresenter provideRecipesPresenter(RecipesMvpView view, RecipesInteractor interactor) {
        return new RecipesPresenter(view, interactor);
    }

    @Provides
    RecipeDetailView provideRecipeDetailView() {
        return new RecipeDetailFragment();
    }

    @Provides
    RecipeDetailInteractor provideRecipeDetailInteractor(Context context) {
        return new RecipeDetailInteractor(context);
    }

    @Provides
    RecipeDetailPresenter provideRecipeDetailPresenter(RecipeDetailView view, RecipeDetailInteractor interactor) {
        return new RecipeDetailPresenter(view, interactor);
    }
}
