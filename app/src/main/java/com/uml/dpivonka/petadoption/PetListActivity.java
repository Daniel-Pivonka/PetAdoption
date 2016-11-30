package com.uml.dpivonka.petadoption;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bbindas on 11/28/16.
 */
public class PetListActivity extends AppCompatActivity {

    private PetAdapter mAdapter;

    private TextView mEmptyStateTextView;

    private ArrayList<Pets> pets = new ArrayList<Pets>();
    // this will be used to display the list of pet options
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        ListView petListView = (ListView) findViewById(R.id.list);


        mAdapter = new PetAdapter(this, new ArrayList<Pets>());
        petListView.setAdapter(mAdapter);

        Intent intent = getIntent();
        String[] pets = intent.getStringArrayExtra("preferences");
        startAysnc(new ArrayList<String>(Arrays.asList(pets)));

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            startAysnc(new ArrayList<String>(Arrays.asList(pets)));
        } else {
            // Otherwise, display error
            // First, hide loading indicator so error message will be visible
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText("no internet connection");
        }

        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                // Find the current pet that was clicked on
                Pets currentPet = mAdapter.getItem(position);

            }
        });
    }

    public void startAysnc(ArrayList<String> preferences){
        PetAsyncTask task = new PetAsyncTask();
        task.execute(preferences);
    }

    public class PetAsyncTask extends AsyncTask<ArrayList<String>, Void, ArrayList<Pets>>
    {
        @Override
        protected ArrayList<Pets> doInBackground(ArrayList<String>... params) {
            ArrayList<String> preferences = null;
            for (ArrayList<String> item : params) {
                preferences = item;
            }
            return Utils.fetchAnimalData(preferences);
        }

        protected void onPostExecute(ArrayList<Pets> pets) {

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            //mEmptyStateTextView.setText("no pets found");
            System.out.println("");

            mAdapter.clear();

            if (pets != null && !pets.isEmpty()) {
                mAdapter.addAll(pets);
            }

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void menuOnClickHandler(MenuItem item) {
        Intent intent = new Intent(this, FavoritesActivity.class);
        startActivity(intent);
    }
}
