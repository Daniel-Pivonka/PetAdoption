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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

        /*TextView petDescription = (TextView) listItemView.findViewById(R.id.description);
        petDescription.setText(currentPet.getDescription());*/

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.petImage);
        if(!currentPet.getPhotoUrl().isEmpty()) {
            int index_largest=0, old_width=0, new_width=0;
            Pattern p = Pattern.compile("(?:&width=)([0-9]+)");
            Matcher m = p.matcher(currentPet.getPhotoUrl().get(0));
            m.find();
            old_width = Integer.parseInt(m.group(1));

            for(int x = 1; x < currentPet.getPhotoUrl().size(); x++) {
                m = p.matcher(currentPet.getPhotoUrl().get(x));
                m.find();
                new_width = Integer.parseInt(m.group(1));
                if(new_width > old_width) {
                    index_largest = x;
                }
                old_width = new_width;
            }
            ImageLoader.getInstance().displayImage(currentPet.getPhotoUrl().get(index_largest), imageView);
        }

        return listItemView;
    }
}
