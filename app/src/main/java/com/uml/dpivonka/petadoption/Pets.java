package com.uml.dpivonka.petadoption;

import java.util.ArrayList;

/**
 * Created by dpivonka on 11/15/2016.
 */
public class Pets {
    private String Name;
    private String Sex;
    private String Animal;
    private String Breed;
    private String Age;
    private String Size;
    private ArrayList<String> Options;
    private String Description;
    private ArrayList<String> PhotoUrl;
    private String Contact;

    Pets(String name, String sex, String animal, String breed, String age, String size, ArrayList<String> options, String description, ArrayList<String> photoUrl, String contact) {
        Name = name;
        Sex = sex;
        Animal = animal;
        Breed = breed;
        Age = age;
        Size = size;
        Options = options;
        Description = description;
        PhotoUrl = photoUrl;
        Contact = contact;
    }

    public String getName() {
        return Name;
    }

    public String getSex() {
        return Sex;
    }

    public String getAnimal() {
        return Animal;
    }

    public String getBreed() {
        return Breed;
    }

    public String getAge() {
        return Age;
    }

    public String getSize() {
        return Size;
    }

    public ArrayList<String> getOptions() {
        return Options;
    }

    public String getDescription() {
        return Description;
    }

    public ArrayList<String> getPhotoUrl() {
        return PhotoUrl;
    }

    public String getContact() {
        return Contact;
    }
}
