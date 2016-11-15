package com.uml.dpivonka.petadoption;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> preferences = new ArrayList<String>();
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
            System.out.println("Done with internet stuff\n");
        }
    }
}
