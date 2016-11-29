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

<<<<<<< HEAD
        final Button button = (Button) findViewById(R.id.button3);
=======
>>>>>>> 1f08b3aa14f7b83b679f68c2f5f6096a99c9f362

        Intent intent = getIntent();
        final String location = intent.getStringExtra("location");

        //if button is clicked
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                // Create a new intent to open the {@link NumbersActivity}
                Intent petTypeIntent = new Intent(PetTypeActivity.this, PetListActivity.class);
                ArrayList<String> checkedSpecs = onCheckboxClicked(v);
                checkedSpecs.add(location);
                startAysnc(location); // change this to take an ArrayList<String> -> checkSpecs
            }
        });

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

<<<<<<< HEAD
    public ArrayList<String> onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();
        ArrayList<String> petSpecs = new ArrayList<String>();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.barnyard:
                if (checked)
                    petSpecs.add("barnyard");
                else {
                    if (!petSpecs.contains("barnyard"))
                        break;
                    else
                        petSpecs.remove("barnyard");
                }
                break;
            case R.id.bird:
                if (checked)
                    petSpecs.add("bird");
                else {
                    if (!petSpecs.contains("bird"))
                        break;
                    else
                        petSpecs.remove("bird");
                }
                break;
            case R.id.cat:
                if (checked)
                    petSpecs.add("cat");
                else {
                    if (!petSpecs.contains("cat"))
                        break;
                    else
                        petSpecs.remove("cat");
                }
                break;
            case R.id.dog:
                if (checked)
                    petSpecs.add("dog");
                else {
                    if (!petSpecs.contains("dog"))
                        break;
                    else
                        petSpecs.remove("dog");
                }
                break;
            case R.id.horse:
                if (checked)
                    petSpecs.add("horse");
                else {
                    if (!petSpecs.contains("horse"))
                        break;
                    else
                        petSpecs.remove("horse");
                }
                break;
            case R.id.pig:
                if (checked)
                    petSpecs.add("horse");
                else {
                    if (!petSpecs.contains("horse"))
                        break;
                    else
                        petSpecs.remove("horse");
                }
                break;
            case R.id.reptile:
                if (checked)
                    petSpecs.add("reptile");
                else {
                    if (!petSpecs.contains("reptile"))
                        break;
                    else
                        petSpecs.remove("reptile");
                }
                break;
            case R.id.small_furry:
                if (checked)
                    petSpecs.add("small furry");
                else {
                    if (!petSpecs.contains("small furry"))
                        break;
                    else
                        petSpecs.remove("small furry");
                }
                break;
            case R.id.male:
                if (checked)
                    petSpecs.add("male");
                else {
                    if (!petSpecs.contains("male"))
                        break;
                    else
                        petSpecs.remove("male");
                }
                break;
            case R.id.female:
                if (checked)
                    petSpecs.add("female");
                else {
                    if (!petSpecs.contains("female"))
                        break;
                    else
                        petSpecs.remove("female");
                }
                break;
            case R.id.baby:
                if (checked)
                    petSpecs.add("baby");
                else {
                    if (!petSpecs.contains("baby"))
                        break;
                    else
                        petSpecs.remove("baby");
                }
                break;
            case R.id.young:
                if (checked)
                    petSpecs.add("young");
                else {
                    if (!petSpecs.contains("young"))
                        break;
                    else
                        petSpecs.remove("young");
                }
                break;
            case R.id.adult:
                if (checked)
                    petSpecs.add("adult");
                else {
                    if (!petSpecs.contains("adult"))
                        break;
                    else
                        petSpecs.remove("adult");
                }
                break;
            case R.id.senior:
                if (checked)
                    petSpecs.add("senior");
                else {
                    if (!petSpecs.contains("senior"))
                        break;
                    else
                        petSpecs.remove("senior");
                }
                break;
            case R.id.small:
                if (checked)
                    petSpecs.add("small");
                else {
                    if (!petSpecs.contains("small"))
                        break;
                    else
                        petSpecs.remove("small");
                }
                break;
            case R.id.medium:
                if (checked)
                    petSpecs.add("medium");
                else {
                    if (!petSpecs.contains("medium"))
                        break;
                    else
                        petSpecs.remove("medium");
                }
                break;
            case R.id.large:
                if (checked)
                    petSpecs.add("large");
                else {
                    if (!petSpecs.contains("large"))
                        break;
                    else
                        petSpecs.remove("large");
                }
                break;
            case R.id.extra_large:
                if (checked)
                    petSpecs.add("extra large");
                else {
                    if (!petSpecs.contains("extra large"))
                        break;
                    else
                        petSpecs.remove("extra large");
                }
                break;
        }
        return petSpecs;
=======
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
>>>>>>> 1f08b3aa14f7b83b679f68c2f5f6096a99c9f362
    }

}
