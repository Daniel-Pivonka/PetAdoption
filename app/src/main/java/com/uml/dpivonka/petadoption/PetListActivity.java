package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

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

//        Intent intent = getIntent();
//        ArrayList<Pets> pets = intent.getS("result");


    }


}
