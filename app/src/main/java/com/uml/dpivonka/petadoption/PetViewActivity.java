package com.uml.dpivonka.petadoption;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uml.dpivonka.petadoption.contentprovider.FavoritesContentProvider;
import com.uml.dpivonka.petadoption.database.FavoritesTable;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        LinearLayout ll = (LinearLayout)findViewById(R.id.LinearLayout1);
        for(int i=0; i<pet.getPhotoUrl().size() ; i++)
        {
            ImageView ii= new ImageView(this);
            ImageLoader.getInstance().displayImage(pet.getPhotoUrl().get(i), ii);
            ll.addView(ii);
        }

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                AddPetToFavorites(pet, FavoritesContentProvider.CONTENT_URI);
                Toast.makeText(getApplicationContext(),
                        "Added to Favorites", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void AddPetToFavorites(Pets pet, Uri uri) {

        ContentValues values = new ContentValues();
        values.put(FavoritesTable.COLUMN_NAME, pet.getName());
        values.put(FavoritesTable.COLUMN_GENDER, pet.getSex());
        values.put(FavoritesTable.COLUMN_ANIMAL, pet.getAnimal());
        values.put(FavoritesTable.COLUMN_BREED, pet.getBreed());
        values.put(FavoritesTable.COLUMN_AGE, pet.getAge());
        values.put(FavoritesTable.COLUMN_SIZE, pet.getSize());
        values.put(FavoritesTable.COLUMN_DESCRIPTION, pet.getDescription());
        values.put(FavoritesTable.COLUMN_NAME, pet.getName());
        //get biggest image
        int index_largest=0, old_width=0, new_width=0;
        Pattern p = Pattern.compile("(?:&width=)([0-9]+)");
        Matcher m = p.matcher(pet.getPhotoUrl().get(0));
        m.find();
        old_width = Integer.parseInt(m.group(1));

        for(int x = 1; x < pet.getPhotoUrl().size(); x++) {
            m = p.matcher(pet.getPhotoUrl().get(x));
            m.find();
            new_width = Integer.parseInt(m.group(1));
            if(new_width > old_width) {
                index_largest = x;
            }
            old_width = new_width;
        }
        values.put(FavoritesTable.COLUMN_IMAGE_URL, pet.getPhotoUrl().get(index_largest));

        getContentResolver().insert(uri, values);

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
