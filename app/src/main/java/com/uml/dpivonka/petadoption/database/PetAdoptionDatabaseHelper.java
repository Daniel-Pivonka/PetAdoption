package com.uml.dpivonka.petadoption.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Scott on 11/20/2016.
 */

//extends SQLiteOpenHelper and calls the static methods of the FavoritesTable helper class.
public class PetAdoptionDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "favoritestable.db";
    public static final int DATABASE_VERSION = 1;

    public PetAdoptionDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Method is called during creation of the database
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        FavoritesTable.onCreate(sqLiteDatabase);
    }

    // Method is called during an upgrade of the database,
    // e.g. if you increase the database version
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        FavoritesTable.onUpgrade(sqLiteDatabase, oldVersion, newVersion);
    }
}
