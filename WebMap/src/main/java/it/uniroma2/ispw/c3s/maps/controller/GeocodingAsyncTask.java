package it.uniroma2.ispw.c3s.maps.controller;

import it.uniroma2.ispw.c3s.maps.model.LatLng;

public class GeocodingAsyncTask extends Thread {
    private String address;
    private LatLng latLng;

    public GeocodingAsyncTask(String address) {
        this.address = address;
        this.latLng = null;
    }

    @Override
    public void run() {
        this.latLng = ValidateAddressController.geocodeAddress(this.address);
    }

    public LatLng getLatLng() {
        return latLng;
    }
}
