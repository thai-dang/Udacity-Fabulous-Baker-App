package com.example.android.udacity_baking_app;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.udacity_baking_app.ui.recipes.MainActivity;
import com.example.android.udacity_baking_app.ui.recipes.RecipeListFragment;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RecipeListActivityTest {

    private IdlingResource mIdlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        mIdlingResource = RecipeListFragment.getInstance().getIdlingResource();
        IdlingRegistry.getInstance().register(mIdlingResource);
    }

    @Test
    public void recyclerViewItem_Click_OpenListActivity() {
        onView(withId(R.id.recipeListRecyclerView)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
    }

    @After
    public void unRegisterIdlingResource() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }
    }
}
