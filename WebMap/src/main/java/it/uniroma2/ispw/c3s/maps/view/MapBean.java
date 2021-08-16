package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.model.Icon;
import it.uniroma2.ispw.c3s.maps.model.LatLng;

import java.util.List;

public class MapBean {
    private LatLng center;
    private int zoom;
    private List<Icon> iconList;

    public MapBean() {
        this.center = null;
        this.zoom = 0;
        this.iconList = null;
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