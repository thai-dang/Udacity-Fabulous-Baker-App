package com.example.android.udacity_baking_app.utils;

import android.content.Context;
import android.content.res.Configuration;

public class ConfigLayoutUtils {
    public static boolean isTabletMode(Context context) {
        return (context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_XLARGE;
    }

    public static boolean isLandScape(Context context) {
        return Configuration.ORIENTATION_LANDSCAPE == context.getResources().getConfiguration().orientation;
    }
}
