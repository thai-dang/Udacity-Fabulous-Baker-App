package com.example.android.udacity_baking_app.database.local;

import android.net.Uri;

import net.simonvt.schematic.annotation.ContentProvider;
import net.simonvt.schematic.annotation.ContentUri;
import net.simonvt.schematic.annotation.TableEndpoint;

@ContentProvider(authority = BakingProvider.AUTHORITY, database = BakingDB.class)
public class BakingProvider {

    static final String AUTHORITY = "com.example.android.udacity_baking_app.local.provider";

    @TableEndpoint(table = BakingDB.TABLE_NAME)
    public static class RecipeIngredients {

        @ContentUri(path = "ingredients", type = "vnd.android.cursor.dir/ingredients")
        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/ingredients");
    }
}
