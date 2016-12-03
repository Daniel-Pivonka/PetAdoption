package com.uml.dpivonka.petadoption;

import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
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

import com.uml.dpivonka.petadoption.database.FavoritesTable;
import com.uml.dpivonka.petadoption.contentprovider.FavoritesContentProvider;

public class FavoritesActivity extends ListActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int DELETE_ID = Menu.FIRST + 1;

    private SimpleCursorAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.favorites_list);
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
        super.onListItemClick(l, v, position, id);
    }

    private void fillData() {
        //fields from the database
        //must include the _id column for the adapter to work
        String[] from = new String[] {
                FavoritesTable.COLUMN_NAME,
                FavoritesTable.COLUMN_GENDER,
                FavoritesTable.COLUMN_ANIMAL,
                FavoritesTable.COLUMN_BREED,
                FavoritesTable.COLUMN_AGE,
                FavoritesTable.COLUMN_SIZE,
                FavoritesTable.COLUMN_DESCRIPTION };
        //fields on the ui to which we map
        int[] to = new int[] {R.id.name, R.id.gender, R.id.animal, R.id.breed, R.id.age, R.id.size, };

        //TODO: map pet image to layout view

        getLoaderManager().initLoader(0, null, this);
        adapter = new SimpleCursorAdapter(this, R.layout.favorites_row, null, from, to, 0);

        setListAdapter(adapter);
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
                FavoritesTable.COLUMN_IMAGE_URL  };
        CursorLoader cursorLoader = new CursorLoader(this, FavoritesContentProvider.CONTENT_URI, projection, null, null, null);

        return cursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.swapCursor(null);
    }
}
