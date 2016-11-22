package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
}
