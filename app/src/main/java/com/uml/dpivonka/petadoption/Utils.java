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

        String url = "http://api.petfinder.com/pet.find?key=0657eb03f8e01bbe903b5adacdd5bf8c&format=json&count=150";

        //adding preferences
        if(preferences.contains("barnyard")){
            url += "&animal=barnyard";
        }
        if(preferences.contains("bird")){
            url += "&animal=bird";
        }
        if(preferences.contains("cat")){
            url += "&animal=cat";
        }
        if(preferences.contains("horse")){
            url += "&animal=horse";
        }
        if(preferences.contains("dog")){
            url += "&animal=dog";
        }
        if(preferences.contains("pig")){
            url += "&animal=pig";
        }
        if(preferences.contains("reptile")){
            url += "&animal=reptile";
        }
        if(preferences.contains("smallfurry")){
            url += "&animal=smallfurry";
        }
        if(preferences.contains("male")){
            url += "&sex=M";
        }
        if(preferences.contains("female")){
            url += "&sex=F";
        }
        if(preferences.contains("baby")){
            url += "&age=baby";
        }
        if(preferences.contains("young")){
            url += "&age=young";
        }
        if(preferences.contains("adult")){
            url += "&age=adult";
        }
        if(preferences.contains("senior")){
            url += "&age=senior";
        }
        if(preferences.contains("small")){
            url += "&size=S";
        }
        if(preferences.contains("medium")){
            url += "&size=M";
        }
        if(preferences.contains("large")){
            url += "&size=L";
        }
        if(preferences.contains("extra-large")){
            url += "&size=XL";
        }

        url += "&location=" + preferences.get(preferences.size()-1);

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

                //create options array
                ArrayList<String> options = new ArrayList<String>();
                JSONObject ops = jsonPet.getJSONObject("options");
                if(ops.has("option")) {
                    try {
                        JSONArray op = ops.getJSONArray("option");
                        for (int y = 0; y < op.length(); y++) {

                            options.add(op.getJSONObject(y).getString("$t"));
                        }
                    } catch (JSONException e) {
                        JSONObject op = ops.getJSONObject("option");
                        options.add(op.getString("$t"));
                    }
                }

                //create photo url array
                ArrayList<String> photoUrl = new ArrayList<String>();
                JSONObject photos = jsonPet.getJSONObject("media");
                if(photos.has("photos")) {
                    photos = photos.getJSONObject("photos");
                    JSONArray pho = photos.getJSONArray("photo");
                    for (int w = 0; w < pho.length(); w++) {
                        photoUrl.add(pho.getJSONObject(w).getString("$t"));
                    }
                }

                //create contact string
                String contact = "";
                JSONObject cont = jsonPet.getJSONObject("contact");
                contact += cont.getString("address1") + " ";
                contact += cont.getString("city") + " ";
                contact += cont.getString("state") + " ";
                contact += cont.getString("zip") + " ";
                contact += cont.getString("phone") + " ";
                contact += cont.getString("email");

                //create new pet
                Pets pet = new Pets(jsonPet.getJSONObject("name").getString("$t"), jsonPet.getJSONObject("sex").getString("$t"),
                                    jsonPet.getJSONObject("animal").getString("$t"), jsonPet.getJSONObject("breeds").getString("breed"),
                                    jsonPet.getJSONObject("age").getString("$t"), jsonPet.getJSONObject("size").getString("$t"), options,
                                    jsonPet.getString("description"), photoUrl, contact);

                //add pet to array
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
            Log.e(LOG_TAG, "Problem retrieving the Petfinder JSON results.", e);
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
