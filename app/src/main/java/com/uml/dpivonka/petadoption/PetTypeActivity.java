package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bbindas on 11/21/16.
 */
public class PetTypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);
    }

    public void startAysnc(String location){
        System.out.println(location);
        ArrayList<String> preferences = new ArrayList<String>();
        preferences.add(location);
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

            //scott take this array of pets and fill the sql database with it
            for (int x = 0; x < pets.size(); x++) {
                System.out.println(pets.get(x).getName());
            }

        // make an array of the checked items for Scott to use in
        // parsing through the data base
        //boolean isChecked = ((CheckBox) findViewById(R.id.))
        }

    }

    public ArrayList<String> submitPreferences(View view) {
        ArrayList<String> petSpecs = new ArrayList<String>();

        CheckBox cb = (CheckBox) findViewById(R.id.barnyard);
        if (cb.isChecked()) {
            petSpecs.add("barnyard");
        }
        cb = (CheckBox) findViewById(R.id.bird);
        if (cb.isChecked()) {
            petSpecs.add("bird");
        }
        cb = (CheckBox) findViewById(R.id.bird);
        if (cb.isChecked()) {
            petSpecs.add("bird");
        }
        cb = (CheckBox) findViewById(R.id.cat);
        if (cb.isChecked()) {
            petSpecs.add("cat");
        }
        cb = (CheckBox) findViewById(R.id.horse);
        if (cb.isChecked()) {
            petSpecs.add("horse");
        }
        cb = (CheckBox) findViewById(R.id.dog);
        if (cb.isChecked()) {
            petSpecs.add("dog");
        }
        cb = (CheckBox) findViewById(R.id.pig);
        if (cb.isChecked()) {
            petSpecs.add("pig");
        }
        cb = (CheckBox) findViewById(R.id.reptile);
        if (cb.isChecked()) {
            petSpecs.add("reptile");
        }
        cb = (CheckBox) findViewById(R.id.small_furry);
        if (cb.isChecked()) {
            petSpecs.add("smallfurry");
        }
        cb = (CheckBox) findViewById(R.id.male);
        if (cb.isChecked()) {
            petSpecs.add("male");
        }
        cb = (CheckBox) findViewById(R.id.female);
        if (cb.isChecked()) {
            petSpecs.add("female");
        }
        cb = (CheckBox) findViewById(R.id.baby);
        if (cb.isChecked()) {
            petSpecs.add("baby");
        }
        cb = (CheckBox) findViewById(R.id.young);
        if (cb.isChecked()) {
            petSpecs.add("young");
        }
        cb = (CheckBox) findViewById(R.id.adult);
        if (cb.isChecked()) {
            petSpecs.add("adult");
        }
        cb = (CheckBox) findViewById(R.id.senior);
        if (cb.isChecked()) {
            petSpecs.add("senior");
        }
        cb = (CheckBox) findViewById(R.id.small);
        if (cb.isChecked()) {
            petSpecs.add("small");
        }
        cb = (CheckBox) findViewById(R.id.medium);
        if (cb.isChecked()) {
            petSpecs.add("medium");
        }
        cb = (CheckBox) findViewById(R.id.large);
        if (cb.isChecked()) {
            petSpecs.add("large");
        }
        cb = (CheckBox) findViewById(R.id.extra_large);
        if (cb.isChecked()) {
            petSpecs.add("extra-large");
        }

        Intent intent = getIntent();
        final String location = intent.getStringExtra("location");
        petSpecs.add(location);
        startAysnc(location); // change this to take an ArrayList<String> -> checkSpecs
        intent = new Intent(PetTypeActivity.this, PetListActivity.class);
        startActivity(intent);

        return petSpecs;

    }

}
