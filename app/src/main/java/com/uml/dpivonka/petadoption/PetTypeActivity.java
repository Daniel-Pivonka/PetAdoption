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

    private static String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);
    }



    public void submitPreferences(View view) {
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
        if(intent.getStringExtra("location") != null) {
            location = intent.getStringExtra("location");
        }
        petSpecs.add(location);
        intent = new Intent(PetTypeActivity.this, PetListActivity.class);


        intent.putExtra("preferences", petSpecs.toArray(new String[petSpecs.size()]));
        startActivity(intent);
    }

}
