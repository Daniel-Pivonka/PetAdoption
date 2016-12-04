package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by bbindas on 11/21/16.
 */
public class PetTypeActivity extends AppCompatActivity {

    private static String location;
    boolean checkBox = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);
    }



    public void submitPreferences(View view) {
        ArrayList<String> petSpecs = new ArrayList<String>();

        RadioButton rb = (RadioButton) findViewById(R.id.barnyard);
        if (rb.isChecked()) {
            petSpecs.add("barnyard");
        }
        rb = (RadioButton) findViewById(R.id.bird);
        if (rb.isChecked()) {
            petSpecs.add("bird");
        }
        rb = (RadioButton) findViewById(R.id.bird);
        if (rb.isChecked()) {
            petSpecs.add("bird");
        }
        rb = (RadioButton) findViewById(R.id.cat);
        if (rb.isChecked()) {
            petSpecs.add("cat");
        }
        rb = (RadioButton) findViewById(R.id.horse);
        if (rb.isChecked()) {
            petSpecs.add("horse");
        }
        rb = (RadioButton) findViewById(R.id.dog);
        if (rb.isChecked()) {
            petSpecs.add("dog");
        }
        rb = (RadioButton) findViewById(R.id.pig);
        if (rb.isChecked()) {
            petSpecs.add("pig");
        }
        rb = (RadioButton) findViewById(R.id.reptile);
        if (rb.isChecked()) {
            petSpecs.add("reptile");
        }
        rb = (RadioButton) findViewById(R.id.small_furry);
        if (rb.isChecked()) {
            petSpecs.add("smallfurry");
        }
        rb = (RadioButton) findViewById(R.id.male);
        if (rb.isChecked()) {
            petSpecs.add("male");
        }
        rb = (RadioButton) findViewById(R.id.female);
        if (rb.isChecked()) {
            petSpecs.add("female");
        }
        rb = (RadioButton) findViewById(R.id.baby);
        if (rb.isChecked()) {
            petSpecs.add("baby");
        }
        rb = (RadioButton) findViewById(R.id.young);
        if (rb.isChecked()) {
            petSpecs.add("young");
        }
        rb = (RadioButton) findViewById(R.id.adult);
        if (rb.isChecked()) {
            petSpecs.add("adult");
        }
        rb = (RadioButton) findViewById(R.id.senior);
        if (rb.isChecked()) {
            petSpecs.add("senior");
        }
        rb = (RadioButton) findViewById(R.id.small);
        if (rb.isChecked()) {
            petSpecs.add("small");
        }
        rb = (RadioButton) findViewById(R.id.medium);
        if (rb.isChecked()) {
            petSpecs.add("medium");
        }
        rb = (RadioButton) findViewById(R.id.large);
        if (rb.isChecked()) {
            petSpecs.add("large");
        }
        rb = (RadioButton) findViewById(R.id.extra_large);
        if (rb.isChecked()) {
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
