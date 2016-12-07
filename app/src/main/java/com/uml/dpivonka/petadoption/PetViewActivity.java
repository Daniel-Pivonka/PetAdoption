package com.uml.dpivonka.petadoption;

import android.content.ContentValues;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uml.dpivonka.petadoption.contentprovider.FavoritesContentProvider;
import com.uml.dpivonka.petadoption.database.FavoritesTable;

import java.util.ArrayList;
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

        // get pet passed
        Intent intent = getIntent();
        pet = intent.getParcelableExtra("pet");

        setupImages();

        TextView name = (TextView) findViewById(R.id.pet_name);
        name.setText(pet.getName());

        TextView sex = (TextView) findViewById(R.id.pet_sex);
        sex.setText(Html.fromHtml("<b>" + "Gender: " + "</b>" + pet.getSex()));

        TextView animal = (TextView) findViewById(R.id.pet_animal);
        animal.setText(Html.fromHtml("<b>" + "Type: " + "</b>" + pet.getAnimal()));

        TextView breed = (TextView) findViewById(R.id.pet_breed);
        breed.setText(Html.fromHtml("<b>" + "Breed: " + "</b>"+ pet.getBreed()));

        TextView age = (TextView) findViewById(R.id.pet_age);
        age.setText(Html.fromHtml("<b>" + "Age: " + "</b>" + pet.getAge()));

        TextView size = (TextView) findViewById(R.id.pet_size);
        size.setText(Html.fromHtml("<b>" + "Size: " + "</b>" + pet.getSize()));

        TextView options = (TextView) findViewById(R.id.pet_options);

        if(pet.getOptions().size() > 0) {
            options.setText(Html.fromHtml("<b>" + "Quick Facts: " + "</b>" + GetQuickFacts(pet)));
        }

        TextView description = (TextView) findViewById(R.id.pet_description);
        description.setText(Html.fromHtml("<b>" + "Description:" + "</b> " + "\n\t" + pet.getDescription()));

        TextView contact = (TextView) findViewById(R.id.pet_contact);
        contact.setText(Html.fromHtml("<b>"+ "Interested? \n" + "</b>" +  pet.getContact()));

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                try {
                    AddPetToFavorites(pet, FavoritesContentProvider.CONTENT_URI);
                    Toast.makeText(getApplicationContext(),
                            "Added to Favorites", Toast.LENGTH_LONG).show();
                } catch (Exception e) {}

            }
        });
    }

    private void setupImages() {
        //get linear lay out to fill with imangeviews
        LinearLayout ll = (LinearLayout)findViewById(R.id.LinearLayout1);

        // a bunch of complex computations to pick out out the highest
        // resolutions of the different images provided. this prevents
        // duplicate images that have different resolutions and forces
        // the highest resolution to be used
        if(pet.getPhotoUrl().size() > 0) {
            ArrayList<String> same_images = new ArrayList<String>();
            Pattern p = Pattern.compile("([0-9]+)(?=\\/\\?bust=)"); //find unique images
            Matcher m = p.matcher(pet.getPhotoUrl().get(0));
            Pattern px = Pattern.compile("(?:&width=)([0-9]+)"); //find image width
            Matcher mx;
            int index_largest=0, old_width=0, new_width=0;
            m.find();
            int current_image = Integer.parseInt(m.group(1)); //first image number
            same_images.add(pet.getPhotoUrl().get(0));// just add it

            for(int i=1; i < pet.getPhotoUrl().size() ; i++)
            {
                m = p.matcher(pet.getPhotoUrl().get(i));
                m.find();
                if (Integer.parseInt(m.group(1)) == current_image) { //same images numbers
                    same_images.add(pet.getPhotoUrl().get(i));
                } else { // diff image
                    index_largest=0;
                    old_width=0;
                    new_width=0;
                    mx = px.matcher(same_images.get(0));
                    mx.find();
                    old_width = Integer.parseInt(mx.group(1));

                    for(int x = 1; x < same_images.size(); x++) {
                        mx = px.matcher(same_images.get(x));
                        mx.find();
                        new_width = Integer.parseInt(mx.group(1));
                        if(new_width > old_width) {
                            index_largest = x;
                        }
                        old_width = new_width;
                    }
                    ImageView ii= new ImageView(this);//found best resolution
                    ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                            LinearLayout.LayoutParams.MATCH_PARENT);
                    params.gravity = Gravity.CENTER;
                    ii.setLayoutParams(params);
                    ii.setMinimumWidth(this.getResources().getDisplayMetrics().widthPixels);
                    ImageLoader.getInstance().displayImage(same_images.get(index_largest), ii);
                    ll.addView(ii);

                    same_images.clear();
                    current_image = Integer.parseInt(m.group(1));
                    same_images.add(pet.getPhotoUrl().get(i));
                }
            }
            index_largest=0;
            old_width=0;
            new_width=0;
            mx = px.matcher(same_images.get(0));
            mx.find();
            old_width = Integer.parseInt(mx.group(1));

            for(int x = 1; x < same_images.size(); x++) {
                mx = px.matcher(same_images.get(x));
                mx.find();
                new_width = Integer.parseInt(mx.group(1));
                if(new_width > old_width) {
                    index_largest = x;
                }
                old_width = new_width;
            }
            ImageView ii= new ImageView(this);
            ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.gravity = Gravity.CENTER;
            ii.setLayoutParams(params);
            ii.setMinimumWidth(this.getResources().getDisplayMetrics().widthPixels);
            ImageLoader.getInstance().displayImage(same_images.get(index_largest), ii);
            ll.addView(ii);
        }
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
        values.put(FavoritesTable.COLUMN_QUICK_FACTS, GetQuickFacts(pet));
        values.put(FavoritesTable.COLUMN_CONTACT, pet.getContact());

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

    public String GetQuickFacts(Pets pet) {

        String facts = "";

        for (String fact : pet.getOptions()) {
            facts += fact;

            if (fact != pet.getOptions().get(pet.getOptions().size() - 1)) {
                facts += ", ";
            }
        }
        return facts;
    }
}
