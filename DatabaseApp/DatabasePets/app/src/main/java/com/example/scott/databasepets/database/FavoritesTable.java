package com.example.scott.databasepets.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Scott on 11/20/2016.
 */

public class FavoritesTable {

    //database table
    public static final String TABLE_FAVORITES = "favorites";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_PET_TYPE = "type";
    public static final String COLUMN_BREED = "breed";

    //database creation SQL statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_FAVORITES + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_GENDER + " TEXT,"
            + COLUMN_PET_TYPE + " TEXT,"
            + COLUMN_BREED + " TEXT"
            + ");";

    public static void onCreate(SQLiteDatabase database) {

        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion,
                                 int newVersion) {
        Log.w(FavoritesTable.class.getName(), "Upgrading database from version "
                + oldVersion + " to " + newVersion
                + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        onCreate(database);
    }
}
