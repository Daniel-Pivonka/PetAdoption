package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by bbindas on 11/21/16.
 */
public class PetTypeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_type);


        Intent intent = getIntent();
        String location = intent.getStringExtra("location");
        startAysnc(location);
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

//    public void onCheckboxClicked(View view) {
//        // Is the view now checked?
//        boolean checked = ((CheckBox) view).isChecked();
//
//        for (int i = 0; i < getResources().getStringArray(R.array.animal_specs).length - 1; i++) {
//
//        }
//        //for ()
//
//        // Check which checkbox was clicked
//        switch(view.getId()) {
//            case R.id.barnyard:
//                if (checked)
//                // Put some meat on the sandwich
//                //else
//                // Remove the meat
//                break;
//            //case R.id.checkbox_cheese:
//                if (checked)
//                // Cheese me
//                //else
//                // I'm lactose intolerant
//                break;
//            // TODO: Veggie sandwich
//
//        }
    }
}
