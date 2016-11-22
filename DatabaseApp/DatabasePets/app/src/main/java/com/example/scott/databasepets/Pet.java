package com.example.scott.databasepets;

/**
 * Created by Scott on 11/20/2016.
 */

public class Pet {
    private String gender;
    private String type;
    private String breed;

    Pet(){

    }
    public String getGender() {
        return gender;
    }

    public String getType() {
        return type;
    }

    public String getBreed() {
        return breed;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}
