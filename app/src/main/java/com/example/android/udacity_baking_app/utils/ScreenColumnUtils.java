package com.example.android.udacity_baking_app.utils;

import android.app.Activity;
import android.util.DisplayMetrics;

public class ScreenColumnUtils {

    public static int numberOfColumns(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int widthDivider = 600;
        int width = displayMetrics.widthPixels;
        return width / widthDivider;
    }
}
