package it.uniroma2.ispw.c3s.maps.model;

public class LatLng {
    private double lat;
    private double lng;

    public LatLng(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public double getLatitude() {
        return lat;
    }

    public double getLongitude() {
        return lng;
    }

    @Override
    public String toString() {
        return "lat=" + lat + ", lng=" + lng;
    }
}
