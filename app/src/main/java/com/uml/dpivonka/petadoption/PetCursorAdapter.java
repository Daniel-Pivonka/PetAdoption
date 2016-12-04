package com.uml.dpivonka.petadoption;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uml.dpivonka.petadoption.database.FavoritesTable;

/**
 * Created by Scott on 12/3/2016.
 */

public class PetCursorAdapter extends CursorAdapter {

    PetCursorAdapter(Context context, Cursor cursor, ViewGroup parent) {
        super(context, cursor, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.favorites_row, parent, false);
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {

        //get fields to populate
        TextView favNameView = (TextView) view.findViewById(R.id.name);
        TextView favGenderView = (TextView) view.findViewById(R.id.gender);
        TextView favAnimalView = (TextView) view.findViewById(R.id.animal);
        TextView favBreedView = (TextView) view.findViewById(R.id.breed);
        TextView favAgeView = (TextView) view.findViewById(R.id.age);
        TextView favSizeView = (TextView) view.findViewById(R.id.size);
        ImageView favImageView = (ImageView) view.findViewById(R.id.petImage);

        //Extract properties from cursor
        String name = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_NAME));
        String gender = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_GENDER));
        String animal = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_ANIMAL));
        String breed = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_BREED));
        String age = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_AGE));
        String size = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_SIZE));
        String image = cursor.getString(cursor.getColumnIndexOrThrow(FavoritesTable.COLUMN_IMAGE_URL));

        //populate fields with extracted properties
        favNameView.setText(name);
        favGenderView.setText(gender);
        favAnimalView.setText(animal);
        favBreedView.setText(breed);
        favAgeView.setText(age);
        favSizeView.setText(size);

        if(!image.isEmpty()) {
            ImageLoader.getInstance().displayImage(image, favImageView);
        }
    }
}
