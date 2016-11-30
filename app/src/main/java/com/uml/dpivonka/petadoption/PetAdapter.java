package com.uml.dpivonka.petadoption;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.uml.dpivonka.petadoption.Pets;

import java.util.ArrayList;

/**
 * Created by dpivonka on 11/28/2016.
 */

public class PetAdapter extends ArrayAdapter<Pets> {

    public PetAdapter(Context context, ArrayList<Pets> pets) {
        super(context, 0, pets);
    }

    @Override
    public View getView(int positition, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.favorites_row, parent, false);
        }

        Pets currentPet = getItem(positition);

        TextView petName = (TextView) listItemView.findViewById(R.id.name);
        petName.setText(currentPet.getName());

        TextView petGender = (TextView) listItemView.findViewById(R.id.gender);
        petGender.setText(currentPet.getSex());

        TextView petAnimal = (TextView) listItemView.findViewById(R.id.animal);
        petAnimal.setText(currentPet.getAnimal());

        TextView petBreed = (TextView) listItemView.findViewById(R.id.breed);
        petBreed.setText(currentPet.getBreed());

        TextView petAge = (TextView) listItemView.findViewById(R.id.age);
        petAge.setText(currentPet.getAge());

        TextView petSize = (TextView) listItemView.findViewById(R.id.size);
        petSize.setText(currentPet.getSize());

        TextView petDescription = (TextView) listItemView.findViewById(R.id.description);
        petDescription.setText(currentPet.getDescription());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.petImage);
        if(!currentPet.getPhotoUrl().isEmpty()) {
            ImageLoader.getInstance().displayImage(currentPet.getPhotoUrl().get(0), imageView);
        }

        return listItemView;
    }
}
