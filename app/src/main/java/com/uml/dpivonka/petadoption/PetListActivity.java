package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by bbindas on 11/28/16.
 */
public class PetListActivity extends AppCompatActivity {

    private ArrayList<Pets> pets = new ArrayList<Pets>();
    // this will be used to display the list of pet options
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        Intent intent = getIntent();
        String[] pets = intent.getStringArrayExtra("preferences");
        startAysnc(new ArrayList<String>(Arrays.asList(pets)));


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


            for (int x = 0; x < pets.size(); x++) {
                System.out.println(pets.get(x).getName());
            }

        }

    }
}
