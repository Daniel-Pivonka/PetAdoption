package com.example.scott.databasepets.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Scott on 11/20/2016.
 */

public class PetAdoptionDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "favoritestable.db";
    public static final int DATABASE_VERSION = 1;

    public PetAdoptionDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        FavoritesTable.onCreate(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        FavoritesTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
