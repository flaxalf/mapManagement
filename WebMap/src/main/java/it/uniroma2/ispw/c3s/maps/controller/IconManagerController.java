package it.uniroma2.ispw.c3s.maps.controller;

import it.uniroma2.ispw.c3s.maps.model.*;
import it.uniroma2.ispw.c3s.maps.dao.DBConnectionSingleton;

import java.sql.Connection;
import java.sql.SQLException;

public class IconManagerController {

    public static boolean insertIcon(LatLng latlong, String type, String status, String evaluation, Object o)
            throws ClassNotFoundException, SQLException {
        // retrive db connection from singleton class
        DBConnectionSingleton db = DBConnectionSingleton.getInstance();
        Connection con = db.getConnection();

        Icon icon = new Icon(latlong, new IconType(type), new Status(status), new Evaluation(evaluation), o);
        boolean res = icon.insertIntoDB(con);
        return res;
    }
    public static boolean removeIcon(Object o) throws SQLException, ClassNotFoundException {
        // retrive db connection from singleton class
        DBConnectionSingleton db = DBConnectionSingleton.getInstance();
        Connection con = db.getConnection();

        Icon icon = new Icon(null, null, null, null,null, o);
        boolean res = icon.removeFromDB(con);
        return res;
    }
    public static boolean updateIcon(LatLng latlong, String type, String status, String evaluation, Object o)
            throws SQLException, ClassNotFoundException {
        // retrive db connection from singleton class
        DBConnectionSingleton db = DBConnectionSingleton.getInstance();
        Connection con = db.getConnection();

        Icon oldIcon = new Icon(null, null, null, null, null, o);
        Icon newIcon = new Icon(latlong, new IconType(type), new Status(status), new Evaluation(evaluation), o);
        boolean res = oldIcon.updateIntoDB(con, newIcon);
        return res;
    }
}
