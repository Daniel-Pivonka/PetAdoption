package com.example.scott.databasepets;

import java.util.ArrayList;

/**
 * Created by Scott on 11/20/2016.
 */

public class Pets {

    ArrayList<Pet> pets;

    Pets() {

    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }
}
