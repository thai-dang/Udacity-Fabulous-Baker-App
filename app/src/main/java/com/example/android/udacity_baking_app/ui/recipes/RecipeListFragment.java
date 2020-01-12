package com.example.android.udacity_baking_app.ui.recipes;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.IdlingResource;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.android.udacity_baking_app.R;
import com.example.android.udacity_baking_app.base.BaseFragment;
import com.example.android.udacity_baking_app.resource.SimpleIdlingResource;
import com.example.android.udacity_baking_app.model.RecipeResponse;
import com.example.android.udacity_baking_app.ui.detail.RecipeDetailActivity;
import com.example.android.udacity_baking_app.utils.ScreenColumnUtils;
import com.example.android.udacity_baking_app.utils.ConfigLayoutUtils;
import com.example.android.udacity_baking_app.utils.ConstantDefs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecipeListFragment extends BaseFragment implements RecipesMvpView, RecipesRecyclerAdapter.RecipeClickListener {

    @BindView(R.id.recipeListProgressbar)
    ProgressBar progressBar;

    @BindView(R.id.recipeListRecyclerView)
    RecyclerView recyclerView;

    @Inject
    RecipesPresenter presenter;

    RecipesRecyclerAdapter adapter;
    List<RecipeResponse> recipeList = new ArrayList<>();

    private static final String RECIPE_STATE_KEY = "recipe_state_key";

    @Nullable
    private SimpleIdlingResource mIdlingResource;

    @VisibleForTesting
    @NonNull
    public IdlingResource getIdlingResource() {
        if (mIdlingResource == null) {
            mIdlingResource = new SimpleIdlingResource();
        }
        return mIdlingResource;
    }

    private void setIdlingResource(boolean isIdleNow) {
        if (mIdlingResource != null) {
            mIdlingResource.setIdleState(isIdleNow);
        }
    }

    @VisibleForTesting
    @NonNull
    public static RecipeListFragment getInstance() {
        return new RecipeListFragment();
    }

    public RecipeListFragment() {
        // Required empty public constructor
    }

    public static RecipeListFragment newInstance() {

        Bundle args = new Bundle();

        RecipeListFragment fragment = new RecipeListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@android.support.annotation.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getIdlingResource();

        setIdlingResource(false);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutRes(), container, false);
        ButterKnife.bind(this, view);

        presenter.setView(this);

        if (savedInstanceState != null) {
            this.recipeList = (List<RecipeResponse>) savedInstanceState.getSerializable(RECIPE_STATE_KEY);
            presenter.handleScreeRotation(this.recipeList);
        } else {
            presenter.getRecipes();
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable(RECIPE_STATE_KEY, (Serializable) this.recipeList);
        super.onSaveInstanceState(outState);
    }

    @Override
    public int getLayoutRes() {
        return R.layout.fragment_recipe_list;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void renderRecipes(List<RecipeResponse> recipesList) {
        adapter = new RecipesRecyclerAdapter(recipesList, getActivity(), this);

        if (!ConfigLayoutUtils.isTabletMode(getActivity())) {
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), ScreenColumnUtils.numberOfColumns(getActivity())));
        }

        recyclerView.setAdapter(adapter);
        this.recipeList = recipesList;

        setIdlingResource(true);
    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showErrorMessage() {
        Snackbar.make(getActivity().findViewById(R.id.main_layout), "Something was wrong", Snackbar.LENGTH_SHORT).show();

        setIdlingResource(false);
    }

    @Override
    public void showNetworkConnectionError() {
        Snackbar.make(getActivity().findViewById(R.id.main_layout), "Network connection error", Snackbar.LENGTH_SHORT).show();

        setIdlingResource(false);
    }

    @Override
    public void showServerError() {
        Snackbar.make(getActivity().findViewById(R.id.main_layout), "Server not found", Snackbar.LENGTH_SHORT).show();

        setIdlingResource(false);
    }

    @Override
    public void onRecipeClick(RecipeResponse recipeResponse) {
        Intent i = new Intent(getActivity(), RecipeDetailActivity.class);
        i.putExtra(ConstantDefs.RECIPES_EXTRA, recipeResponse);
        startActivity(i);
    }
}
