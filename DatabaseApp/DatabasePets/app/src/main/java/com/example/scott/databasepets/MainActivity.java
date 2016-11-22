package com.example.scott.databasepets;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.scott.databasepets.contentprovider.MyFavoritesContentProvider;
import com.example.scott.databasepets.database.FavoritesTable;
import com.example.scott.databasepets.database.PetAdoptionDatabaseHelper;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.button);
        Button button2 = (Button) findViewById(R.id.button2);
        button.setOnClickListener(onClickListener);
        button2.setOnClickListener(onClickListener);

    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.button:
                    Intent i = new Intent(MainActivity.this, MyFavoritesActivity.class);
                    startActivity(i);
                    break;
                case R.id.button2:
                    Pet pet = new Pet();
                    pet.setType("Dog");
                    pet.setGender("Male");
                    pet.setBreed("Siberian Husky");
                    AddPetToFavorites(pet, MyFavoritesContentProvider.CONTENT_URI);
                    break;
            }
        }
    };

    public void AddPetToFavorites(Pet pet, Uri uri) {

        ContentValues values = new ContentValues();
        values.put(FavoritesTable.COLUMN_PET_TYPE, pet.getType());
        values.put(FavoritesTable.COLUMN_GENDER, pet.getGender());
        values.put(FavoritesTable.COLUMN_BREED, pet.getBreed());

        getContentResolver().insert(uri, values);

    }

}
