package it.uniroma2.ispw.c3s.maps.model;

import java.util.ArrayList;
import java.util.List;


public class Map {
    private LatLng center;
    private int zoom;
    private List<Icon> iconList;

    public Map() {
        // map center in Rome
        center = new LatLng(41.909986, 12.3959123);
        zoom = 5;
        iconList = new ArrayList<>();
    }

    public Map(LatLng center, int zoom, List<Icon> iconList) {
        this.center = center;
        this.zoom = zoom;
        this.iconList = iconList;
    }

    public LatLng getCenter() {
        return center;
    }

    public void setCenter(LatLng center) {
        this.center = center;
    }

    public int getZoom() {
        return zoom;
    }

    public void setZoom(int zoom) {
        this.zoom = zoom;
    }

    public List<Icon> getIconList() {
        return iconList;
    }

    public void setIconList(List<Icon> iconList) {
        this.iconList = iconList;
    }
}
