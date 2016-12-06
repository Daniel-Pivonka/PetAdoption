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

    public TextView mEmptyStateTextView;

    //hold the preferences static to save state on return
    // to check if they have changed
    private static String[] prefs;

    //hold pets returned from aysnc static to prevent
    // unessasary reload
    private static ArrayList<Pets> pets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_list);

        // get views to work with
        TextView mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        ListView petListView = (ListView) findViewById(R.id.list);

        //create adapter
        mAdapter = new PetAdapter(this, new ArrayList<Pets>());
        petListView.setAdapter(mAdapter);

        //get preferences passed
        Intent intent = getIntent();
        boolean pref_changed = false;
        if (intent.getStringArrayExtra("preferences") != null) { // prefs were received
            prefs = intent.getStringArrayExtra("preferences");
            pref_changed = true;
        }

        // Get a reference to the ConnectivityManager to check state of network connectivity
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        // Get details on the currently active default data network
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        // If there is a network connection, fetch data
        if (networkInfo != null && networkInfo.isConnected()) {
            if(pets == null || pref_changed == true){ //need to fetch pets
                pets = new ArrayList<Pets>();
                startAysnc(new ArrayList<String>(Arrays.asList(prefs)));
            } else { // already have pets or problem
                View loadingIndicator = findViewById(R.id.loading_indicator);
                loadingIndicator.setVisibility(View.GONE);

                mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
                mEmptyStateTextView.setText("no pets found");

                mAdapter.clear();

                if (pets != null && !pets.isEmpty()) { // make sure we have pets
                    mAdapter.addAll(pets);//fill adapter
                    mEmptyStateTextView.setText("");
                }
            }
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
                //create intent with selected pet attached
                Intent intent = new Intent(PetListActivity.this, PetViewActivity.class).putExtra("pet", currentPet);
                startActivity(intent);

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

        protected void onPostExecute(ArrayList<Pets> petss) {

            pets = petss;

            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
            mEmptyStateTextView.setText("no pets found");


            mAdapter.clear();

            if (pets != null && !pets.isEmpty()) {
                mAdapter.addAll(pets);
                mEmptyStateTextView.setText("");
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
