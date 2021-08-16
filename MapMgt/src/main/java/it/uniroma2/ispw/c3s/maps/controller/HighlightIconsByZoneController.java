package it.uniroma2.ispw.c3s.maps.controller;

import it.uniroma2.ispw.c3s.maps.dao.DBConnectionSingleton;
import it.uniroma2.ispw.c3s.maps.dao.IconCRUD;
import it.uniroma2.ispw.c3s.maps.model.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class HighlightIconsByZoneController {

    public static Map highlightIconsByZone(LatLng center, String type) throws ClassNotFoundException, SQLException {
        // retrive db connection from singleton class
        DBConnectionSingleton db = DBConnectionSingleton.getInstance();
        Connection con = db.getConnection();

        List<Icon> list = Icon.findIconsFromDB(con, new IconType(type));
        return new Map(center,10, list);
    }
}
