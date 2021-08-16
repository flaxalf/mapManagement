package it.uniroma2.ispw.c3s.maps.controller;

import it.uniroma2.ispw.c3s.maps.model.LatLng;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;

public class ValidateAddressController {
    private static final String KEY = "AIzaSyAEi2uPbwjnFTNQRpZ_bt4_1QbspH6sDVI";
    private static final String REQUEST = "https://maps.googleapis.com/maps/api/geocode/json?address=%s&key=%s";
    private static final String REVERSE = "https://maps.googleapis.com/maps/api/geocode/json?latlng=%f,%f&key=%s";

    public static LatLng geocodeAddress(String address) {
        String json = getJsonString(address);
        return getLatLng(json);
    }

    public static String reverseGeocode(LatLng latLng) {
        String json = getJsonString(latLng);
        return getAddress(json);
    }

    private static String getJsonString(LatLng latLng) {
        String json;
        try {
            // build reverse geocoding HTTP request with lat e lon in dot-decimal notation and API key
            URL url = new URL(String.format(Locale.US, REVERSE, latLng.getLatitude(), latLng.getLongitude(), KEY));
            json = getJsonString(url);
        } catch (MalformedURLException e) {
            json = null;
        }
        return json;
    }

    private static String getJsonString(String address) {
        String json;
        try {
            // build geocoding HTTP request with adjusted address and API key
            URL url = new URL(String.format(REQUEST, address.replace(" ", "+").toLowerCase(), KEY));
            json = getJsonString(url);
        } catch (MalformedURLException e) {
            json = null;
        }
        return json;
    }

    private static String getJsonString(URL url) {
        String json = "", inputLine;

        try {
            URLConnection uc = url.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));

            // read line by line
            while ((inputLine = in.readLine()) != null) {
                // build the full json file
                json = json.concat(inputLine);
            }

            in.close();
        } catch (Exception e) {
            json = null;
        }

        return json;
    }

    private static LatLng getLatLng(String json) {
        JSONObject result = getFirstResult(json);
        // no result found or invalid request
        if (result == null) return null;

        JSONObject geometry = result.getJSONObject("geometry");
        JSONObject location = geometry.getJSONObject("location");
        return new LatLng(location.getDouble("lat"), location.getDouble("lng"));
    }

    private static String getAddress(String json) {
        JSONObject result = getFirstResult(json);
        // no result found or invalid request
        if (result == null) return null;

        return result.getString("formatted_address");
    }

    private static JSONObject getFirstResult(String json) {
        if (json == null) return null;

        JSONObject object = new JSONObject(json);
        String status = object.getString("status");
        if (!status.equals("OK")) return null;

        JSONArray array = object.getJSONArray("results");
        return array.getJSONObject(0);
    }
}