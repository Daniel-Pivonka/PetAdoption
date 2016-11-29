package com.uml.dpivonka.petadoption.contentprovider;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.uml.dpivonka.petadoption.Pets;
import com.uml.dpivonka.petadoption.database.FavoritesTable;
import com.uml.dpivonka.petadoption.database.PetAdoptionDatabaseHelper;

import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by Scott on 11/20/2016.
 */

public class FavoritesContentProvider extends ContentProvider {

    //database
    private PetAdoptionDatabaseHelper database;

    //used for UriMacher
    private static final int FAVORITES = 10;
    private static final int FAVORITES_ID = 20;

    private static final String AUTHORITY = "com.uml.dpivonka.petadoption.contentprovider";

    private static final String BASE_PATH = "favorites";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + BASE_PATH);

    public static final String CONTENT_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE + "/favorites";
    public static final String CONTENT_ITEM_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE + "/favorites";

    private static final UriMatcher sURIMatcher = new UriMatcher(UriMatcher.NO_MATCH);

    static {
        sURIMatcher.addURI(AUTHORITY, BASE_PATH, FAVORITES);
        sURIMatcher.addURI(AUTHORITY, BASE_PATH + "/#", FAVORITES_ID);
    }

    @Override
    public boolean onCreate() {
        database = new PetAdoptionDatabaseHelper(getContext());
        return false;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        //using SQLiteQueryBuilder instead of the query() method
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

        //check if the caller has requested a column which does not exist
        CheckColumns(projection);

        //set the table
        queryBuilder.setTables(FavoritesTable.TABLE_FAVORITES);

        int uriType = sURIMatcher.match(uri);
        switch (uriType) {
            case FAVORITES:
                break;
            case FAVORITES_ID:
                //adding the ID to the original query
                queryBuilder.appendWhere(FavoritesTable.COLUMN_ID + "=" + uri.getLastPathSegment());
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }

        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        Cursor cursor = queryBuilder.query(sqLiteDatabase, projection, selection, selectionArgs, null, null, sortOrder);

        //make sure that potential listeners are getting notified
        cursor.setNotificationUri(getContext().getContentResolver(), uri);

        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        long id = 0;
        switch (uriType) {
            case FAVORITES:
                id = sqLiteDatabase.insert(FavoritesTable.TABLE_FAVORITES, null, contentValues);
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return  Uri.parse(BASE_PATH + "/" + id);
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        int rowsDeleted = 0;
        switch (uriType) {
            case FAVORITES:
                rowsDeleted = sqLiteDatabase.delete(FavoritesTable.TABLE_FAVORITES, selection, selectionArgs);
                break;
            case FAVORITES_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = sqLiteDatabase.delete(FavoritesTable.TABLE_FAVORITES,
                            FavoritesTable.COLUMN_ID + "=" + id,
                            null);
                }
                else {
                    rowsDeleted = sqLiteDatabase.delete(FavoritesTable.TABLE_FAVORITES,
                            FavoritesTable.COLUMN_ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw  new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return  rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {

        int uriType = sURIMatcher.match(uri);
        SQLiteDatabase sqLiteDatabase = database.getWritableDatabase();
        int rowsUpdated = 0;
        switch (uriType) {
            case FAVORITES:
                rowsUpdated = sqLiteDatabase.update(FavoritesTable.TABLE_FAVORITES,
                        contentValues, selection, selectionArgs);
                break;
            case FAVORITES_ID:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsUpdated = sqLiteDatabase.update(FavoritesTable.TABLE_FAVORITES,
                            contentValues, FavoritesTable.COLUMN_ID + "=" + id, null);
                }
                else {
                    rowsUpdated = sqLiteDatabase.update(FavoritesTable.TABLE_FAVORITES,
                            contentValues,
                            FavoritesTable.COLUMN_ID + "=" + id + " and " + selection,
                            selectionArgs);
                }
                break;
            default:
                throw new IllegalArgumentException("Unknown URI: " + uri);
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowsUpdated;

    }

    private void CheckColumns(String[] projection) {
        String[] available = {
                FavoritesTable.COLUMN_NAME,
                FavoritesTable.COLUMN_GENDER,
                FavoritesTable.COLUMN_ANIMAL,
                FavoritesTable.COLUMN_BREED,
                FavoritesTable.COLUMN_AGE,
                FavoritesTable.COLUMN_SIZE,
                FavoritesTable.COLUMN_DESCRIPTION,
                FavoritesTable.COLUMN_IMAGE_URL,
                FavoritesTable.COLUMN_ID };

        if (projection != null) {
            HashSet<String> requestColumns = new HashSet<String>(Arrays.asList(projection));
            HashSet<String> availableColumns = new HashSet<String>(Arrays.asList(available));

            //check if all columns which are requested are available
            if (!availableColumns.containsAll(requestColumns)) {
                throw new IllegalArgumentException("Unknown columns in projection");
            }
        }
    }
}
