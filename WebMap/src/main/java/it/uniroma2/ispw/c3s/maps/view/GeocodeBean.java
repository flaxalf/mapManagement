package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.model.LatLng;

public class GeocodeBean {
    private String address;
    private LatLng point;

    public GeocodeBean() {
        this.address = null;
        this.point = null;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LatLng getPoint() {
        return point;
    }

    public void setPoint(LatLng point) {
        this.point = point;
    }
}
