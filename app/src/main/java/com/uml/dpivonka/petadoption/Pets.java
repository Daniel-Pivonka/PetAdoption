package com.uml.dpivonka.petadoption;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by dpivonka on 11/15/2016.
 */
public class Pets implements Parcelable {
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

    public Pets(String name, String sex, String animal, String breed, String age, String size, ArrayList<String> options, String description, ArrayList<String> photoUrl, String contact) {
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

    protected Pets(Parcel in) {
        Name = in.readString();
        Sex = in.readString();
        Animal = in.readString();
        Breed = in.readString();
        Age = in.readString();
        Size = in.readString();
        Options = in.createStringArrayList();
        Description = in.readString();
        PhotoUrl = in.createStringArrayList();
        Contact = in.readString();
    }

    public static final Creator<Pets> CREATOR = new Creator<Pets>() {
        @Override
        public Pets createFromParcel(Parcel in) {
            return new Pets(in);
        }

        @Override
        public Pets[] newArray(int size) {
            return new Pets[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Name);
        dest.writeString(Sex);
        dest.writeString(Animal);
        dest.writeString(Breed);
        dest.writeString(Age);
        dest.writeString(Size);
        dest.writeStringList(Options);
        dest.writeString(Description);
        dest.writeStringList(PhotoUrl);
        dest.writeString(Contact);
    }
}
