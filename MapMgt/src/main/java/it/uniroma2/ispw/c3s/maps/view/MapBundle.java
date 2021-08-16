package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.model.Map;

import java.util.ListResourceBundle;

public class MapBundle extends ListResourceBundle {
    private Map map;

    public MapBundle(Map map) {
        this.map = map;
    }

    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"map", map}
        };
    }
}