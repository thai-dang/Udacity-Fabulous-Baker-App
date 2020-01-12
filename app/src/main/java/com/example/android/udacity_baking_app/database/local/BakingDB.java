package com.example.android.udacity_baking_app.database.local;

import net.simonvt.schematic.annotation.Database;
import net.simonvt.schematic.annotation.Table;

@Database(fileName = BakingDB.DB_NAME, version = BakingDB.DB_VERSION)
class BakingDB {

    static final String DB_NAME = "bakingdb";
    static final int DB_VERSION = 1;

    @Table(BakingContract.class)
    static final String TABLE_NAME = "ingredients";
}
