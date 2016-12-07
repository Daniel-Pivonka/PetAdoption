package com.uml.dpivonka.petadoption.database;

import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Scott on 11/20/2016.
 */

public class FavoritesTable {

    //database table
    public static final String TABLE_FAVORITES = "favorites";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";
    public static final String COLUMN_ANIMAL= "animal";
    public static final String COLUMN_BREED = "breed";
    public static final String COLUMN_AGE = "age";
    public static final String COLUMN_SIZE = "size";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_IMAGE_URL = "image";
    public static final String COLUMN_QUICK_FACTS = "quick_facts";
    public static final String COLUMN_CONTACT = "contact";

    //database creation SQL statement
    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_FAVORITES + " ("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_NAME + " TEXT,"
            + COLUMN_GENDER + " TEXT,"
            + COLUMN_ANIMAL + " TEXT,"
            + COLUMN_BREED + " TEXT,"
            + COLUMN_AGE + " TEXT,"
            + COLUMN_SIZE + " TEXT,"
            + COLUMN_DESCRIPTION + " TEXT,"
            + COLUMN_IMAGE_URL + " TEXT,"
            + COLUMN_CONTACT + " TEXT,"
            + COLUMN_QUICK_FACTS + " TEXT"
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
