package com.uml.dpivonka.petadoption;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        //get submit button
        Button button = (Button) findViewById(R.id.button2);
        //get zip text feild
        final EditText zip = (EditText) findViewById(R.id.postal_field);

        //if button is clicked
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                //if valid zipcode
                if(zip.getText().toString().matches("^\\d{5}(?:[-\\s]\\d{4})?$")) {
                    startAysnc();
                }

            }
        });

    }

    public void startAysnc(){
        EditText nameField = (EditText) findViewById(R.id.postal_field);
        Editable nameEditable = nameField.getText();
        String name = nameEditable.toString();

        ArrayList<String> preferences = new ArrayList<String>();
        preferences.add(name);
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
        }
    }
}
