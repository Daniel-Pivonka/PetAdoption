package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create default options which will be used for every
        //  displayImage(...) call if no options will be passed to this method
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .defaultDisplayImageOptions(defaultOptions)
                .build();
        ImageLoader.getInstance().init(config); // Do it on Application start

        addListenerOnSpinnerItemSelection();

        //get submit button
        final Button button = (Button) findViewById(R.id.button2);
        //get zip and city,state text fields
        final EditText zip = (EditText) findViewById(R.id.postal_field);
        final EditText city = (EditText) findViewById(R.id.city_field);

        //if button is clicked
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Create a new intent to open the {@link NumbersActivity}
                Intent petTypeIntent = new Intent(MainActivity.this, PetTypeActivity.class);


                //if valid zipcode
                if(zip.getText().toString().matches("^\\d{5}(?:[-\\s]\\d{4})?$")) {
                    petTypeIntent.putExtra("location", zip.getText().toString());
                    startActivity(petTypeIntent);
                } else if (!city.getText().toString().isEmpty() && !(String.valueOf(spinner1.getSelectedItem()) == "")) {
                    petTypeIntent.putExtra("location", city.getText().toString() + ",%20" + String.valueOf(spinner1.getSelectedItem()));
                    startActivity(petTypeIntent);
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Invalid location submission.", Toast.LENGTH_LONG).show();
                    button.setTextColor(Color.RED);
                }
            }
        });

    }


    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.state_field);
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
