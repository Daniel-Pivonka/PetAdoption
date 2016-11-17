package com.uml.dpivonka.petadoption;


import android.util.Log;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by dpivonka on 11/15/2016.
 */

public class Utils {
    public static ArrayList<Pets> fetchAnimalData(ArrayList<String> preferences) {

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {

        }

        makeHttpRequest(createUrl("http://api.petfinder.com/pet.find?key=0657eb03f8e01bbe903b5adacdd5bf8c&animal=dog&animal=cat&location=lowell,%20Massachusetts"));
        return null;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        return jsonResponse;
    }
}
