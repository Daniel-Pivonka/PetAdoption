package com.uml.dpivonka.petadoption;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.uml.dpivonka.petadoption.database.FavoritesTable;
import com.uml.dpivonka.petadoption.contentprovider.FavoritesContentProvider;

import java.util.HashSet;

public class FavoritesActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DELETE_ID = Menu.FIRST + 1;

    private PetCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);
        findViewById(R.id.delete).setVisibility(View.GONE);
        this.getListView().setDividerHeight(2);
        fillData();
        registerForContextMenu(getListView());
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case DELETE_ID:
                AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
                        .getMenuInfo();
                Uri uri = Uri.parse(FavoritesContentProvider.CONTENT_URI + "/"
                        + info.id);
                getContentResolver().delete(uri, null, null);
                fillData();
                return true;
        }
        return  super.onContextItemSelected(item);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        Cursor cursor = adapter.getCursor();

        //Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_NAME));
        String gender = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_GENDER));
        String animal = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_ANIMAL));
        String breed = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_BREED));
        String age = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_AGE));
        String size = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_SIZE));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_IMAGE_URL));
        String description = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_DESCRIPTION));
        String quick_facts = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_QUICK_FACTS));
        String contact = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_CONTACT));

        Intent i = new Intent(getApplicationContext(), FavoritesViewActivity.class);
        i.putExtra(FavoritesTable.COLUMN_NAME, name);
        i.putExtra(FavoritesTable.COLUMN_GENDER, gender);
        i.putExtra(FavoritesTable.COLUMN_ANIMAL, animal);
        i.putExtra(FavoritesTable.COLUMN_BREED, breed);
        i.putExtra(FavoritesTable.COLUMN_AGE, age);
        i.putExtra(FavoritesTable.COLUMN_SIZE, size);
        i.putExtra(FavoritesTable.COLUMN_IMAGE_URL, image);
        i.putExtra(FavoritesTable.COLUMN_DESCRIPTION, description);
        i.putExtra(FavoritesTable.COLUMN_QUICK_FACTS, quick_facts);
        i.putExtra(FavoritesTable.COLUMN_CONTACT, contact);

        startActivity(i);
    }

    private void fillData() {

        getLoaderManager().initLoader(0, null, this);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, DELETE_ID, 0, R.string.menu_delete);
    }


    //creates a new loader after the init call
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                FavoritesTable.COLUMN_ID,
                FavoritesTable.COLUMN_NAME,
                FavoritesTable.COLUMN_GENDER,
                FavoritesTable.COLUMN_ANIMAL,
                FavoritesTable.COLUMN_BREED,
                FavoritesTable.COLUMN_AGE,
                FavoritesTable.COLUMN_SIZE,
                FavoritesTable.COLUMN_DESCRIPTION,
                FavoritesTable.COLUMN_IMAGE_URL,
                FavoritesTable.COLUMN_CONTACT,
                FavoritesTable.COLUMN_QUICK_FACTS };
        CursorLoader cursorLoader = new CursorLoader(this, FavoritesContentProvider.CONTENT_URI, projection, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

        adapter = new PetCursorAdapter(FavoritesActivity.this, cursor, null);

        findViewById(R.id.delete).setVisibility(View.VISIBLE);

        setListAdapter(adapter);
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
