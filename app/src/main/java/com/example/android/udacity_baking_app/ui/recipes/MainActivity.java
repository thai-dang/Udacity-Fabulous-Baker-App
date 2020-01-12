package com.example.android.udacity_baking_app.ui.recipes;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.android.udacity_baking_app.R;
import com.example.android.udacity_baking_app.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.mainToolbar)
    Toolbar mainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        // Set up the toolbar
        setSupportActionBar(mainToolbar);
        mainToolbar.setTitle(R.string.app_name);

        // Set up the recipe list fragment
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_container, RecipeListFragment.newInstance())
                    .commit();
        }
    }
}
