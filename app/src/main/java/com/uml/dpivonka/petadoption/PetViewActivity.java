package com.uml.dpivonka.petadoption;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.nostra13.universalimageloader.core.ImageLoader;

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


    }
}
