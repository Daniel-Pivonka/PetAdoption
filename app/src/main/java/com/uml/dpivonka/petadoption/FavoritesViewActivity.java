package com.uml.dpivonka.petadoption;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uml.dpivonka.petadoption.database.FavoritesTable;

/**
 * Created by Scott on 12/7/2016.
 */

public class FavoritesViewActivity extends AppCompatActivity {

    protected ImageLoader imageLoader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_view);

        Button button = (Button) findViewById(R.id.button);
        button.setVisibility(View.GONE);

        //ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(this).build();
        //ImageLoader.getInstance().init(config);

        Bundle extras = getIntent().getExtras();

        String name = extras.getString(FavoritesTable.COLUMN_NAME);
        String age = extras.getString(FavoritesTable.COLUMN_AGE);
        String animal = extras.getString(FavoritesTable.COLUMN_ANIMAL);
        String breed = extras.getString(FavoritesTable.COLUMN_BREED);
        String gender = extras.getString(FavoritesTable.COLUMN_GENDER);
        String quick_facts = extras.getString(FavoritesTable.COLUMN_QUICK_FACTS);
        String image = extras.getString(FavoritesTable.COLUMN_IMAGE_URL);
        String description = extras.getString(FavoritesTable.COLUMN_DESCRIPTION);
        String size = extras.getString(FavoritesTable.COLUMN_SIZE);
        String contact = extras.getString(FavoritesTable.COLUMN_CONTACT);

        TextView nameView = (TextView) findViewById(R.id.pet_name);
        nameView.setText(name);

        SetImage(image);

        TextView genderView = (TextView) findViewById(R.id.pet_sex);
        TextView typeView = (TextView) findViewById(R.id.pet_animal);
        TextView breedView = (TextView) findViewById(R.id.pet_breed);
        TextView ageView = (TextView) findViewById(R.id.pet_age);
        TextView sizeView = (TextView) findViewById(R.id.pet_size);
        TextView quickFactsView = (TextView) findViewById(R.id.pet_options);
        TextView descriptionView = (TextView) findViewById(R.id.pet_description);
        TextView contactsView = (TextView) findViewById(R.id.pet_contact);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            genderView.setText(Html.fromHtml("<b>" + "Gender: " + "</b>" + gender, Html.FROM_HTML_MODE_LEGACY));
            typeView.setText(Html.fromHtml("<b>" + "Type: " + "</b>" + animal, Html.FROM_HTML_MODE_LEGACY));
            breedView.setText(Html.fromHtml("<b>" + "Breed: " + "</b>" + breed, Html.FROM_HTML_MODE_LEGACY));
            ageView.setText(Html.fromHtml("<b>" + "Age: " + "</b>" + age, Html.FROM_HTML_MODE_LEGACY));
            sizeView.setText(Html.fromHtml("<b>" + "Size: " + "</b>" + size, Html.FROM_HTML_MODE_LEGACY));
            quickFactsView.setText(Html.fromHtml("<b>" + "Quick Facts: " + "</b>" + quick_facts, Html.FROM_HTML_MODE_LEGACY));
            descriptionView.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + description, Html.FROM_HTML_MODE_LEGACY));
            contactsView.setText(Html.fromHtml("<b>"+ "Interested? \n" + "</b>" + contact, Html.FROM_HTML_MODE_LEGACY));

        }
        else {
            genderView.setText(Html.fromHtml("<b>" + "Gender: " + "</b>" + gender));
            typeView.setText(Html.fromHtml("<b>" + "Type: " + "</b>" + animal));
            breedView.setText(Html.fromHtml("<b>" + "Breed: " + "</b>" + breed));
            ageView.setText(Html.fromHtml("<b>" + "Age: " + "</b>" + age));
            sizeView.setText(Html.fromHtml("<b>" + "Size: " + "</b>" + size));
            quickFactsView.setText(Html.fromHtml("<b>" + "Quick Facts: " + "</b>" + quick_facts));
            descriptionView.setText(Html.fromHtml("<b>" + "Description: " + "</b>" + description));
            contactsView.setText(Html.fromHtml("<b>"+ "Interested? \n" + "</b>" + contact));
        }
        Linkify.addLinks(contactsView, Linkify.PHONE_NUMBERS|Linkify.EMAIL_ADDRESSES);
    }
    private void SetImage(String imageUrl) {
        LinearLayout ll = (LinearLayout)findViewById(R.id.LinearLayout1);
        ImageView ii= new ImageView(this);
        ii.setScaleType(ImageView.ScaleType.FIT_CENTER);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.MATCH_PARENT);
        params.gravity = Gravity.CENTER;
        ii.setLayoutParams(params);
        ii.setMinimumWidth(this.getResources().getDisplayMetrics().widthPixels);
        ImageLoader.getInstance().displayImage(imageUrl, ii);
        ll.addView(ii);
    }
}
