package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by dpivonka on 12/4/2016.
 */
public class PetViewActivity extends AppCompatActivity {

    private Pets pet;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_view);
        Intent intent = getIntent();
        pet = intent.getParcelableExtra("pet");


    }
}
