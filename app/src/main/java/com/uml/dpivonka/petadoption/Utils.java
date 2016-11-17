package com.uml.dpivonka.petadoption;


import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

/**
 * Created by dpivonka on 11/15/2016.
 */

public class Utils {

    public static final String LOG_TAG = Utils.class.getSimpleName();

    public static ArrayList<Pets> fetchAnimalData(ArrayList<String> preferences) {

        String url = "http://api.petfinder.com/pet.find?key=0657eb03f8e01bbe903b5adacdd5bf8c&format=json";

        //adding preferences
        url += "&animal=dog&animal=cat&location=lowell,%20Massachusetts";

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(createUrl(url));
        } catch (IOException e) {

        }

        return extractDataFromJson(jsonResponse);
    }

    private static ArrayList<Pets> extractDataFromJson(String jsonResponse) {
        ArrayList<Pets> pets = new ArrayList<Pets>();
        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResponse);
            baseJsonResponse = baseJsonResponse.getJSONObject("petfinder");
            baseJsonResponse = baseJsonResponse.getJSONObject("pets");
            JSONArray jsonPetArray = baseJsonResponse.getJSONArray("pet");

            for(int x = 0; x < jsonPetArray.length(); x++) {
                // Extract out the pet
                JSONObject jsonPet = jsonPetArray.getJSONObject(x);

                //creat new pet
                Pets pet = new Pets();

                //fill pet with info from json

                pets.add(pet);
            }


            return pets;
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the earthquake JSON results", e);
        }
        return null;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }

        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
