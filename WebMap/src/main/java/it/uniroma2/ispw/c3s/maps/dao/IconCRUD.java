package it.uniroma2.ispw.c3s.maps.dao;

import it.uniroma2.ispw.c3s.maps.model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class IconCRUD {

    public static boolean insertIcon(Connection con, Icon icon) throws SQLException {
        boolean found;
        found = checkIcon(con, icon);
        if (found) {//esiste già
            return false;
        }
        int id = getMaxId(con) + 1;
        PreparedStatement statement;
        String query = "insert into icons values(?,?,?,?,?,?,?,?)";
        statement = con.prepareStatement(query);
        statement.setInt(1, id);
        statement.setDouble(2, icon.getLatlong().getLatitude());
        statement.setDouble(3, icon.getLatlong().getLongitude());
        statement.setString(4, icon.getType().getType());
        statement.setString(5, icon.getStatus().getStatus());
        statement.setString(6, icon.getEvaluation().getEvaluation());
        statement.setString(7, icon.getImg().getImgPath());
        statement.setObject(8, icon.getObj());
        statement.executeUpdate();
        icon.setId(new IconId(id));
        return true;
    }

    public static boolean removeIcon(Connection con, Icon icon) throws SQLException {
        boolean found;
        found = checkIcon(con, icon);
        if (!found) {//non esiste
            return false;
        }
        PreparedStatement statement;
        String query = "DELETE from icons WHERE object_id = ?";
        statement = con.prepareStatement(query);
        statement.setObject(1, icon.getObj());
        statement.executeUpdate();
        return true;
    }


    public static boolean updateIcon(Connection con, Icon oldIcon, Icon newIcon) throws SQLException {
        removeIcon(con, oldIcon);
        return insertIcon(con, newIcon);
    }

    private static boolean checkIcon(Connection con, Icon icon) throws SQLException {
        Statement query = con.createStatement();
        Object obj;
        ResultSet result = query.executeQuery("SELECT object_id FROM icons");
        while (result.next()) {
            obj = result.getObject("object_id");

            if (obj == icon.getObj()) {
                return true; //esiste già
            }
        }
        return false; // non esiste
    }

    public static List<Icon> findIcons(Connection con, IconType type) throws SQLException {
        List<Icon> list = new ArrayList<>();
        PreparedStatement statement;
        ResultSet result;
        String query = "SELECT * FROM icons WHERE icon_type = (?)";
        statement = con.prepareStatement(query);
        statement.setString(1, type.getType());
        result = statement.executeQuery();
        while (result.next()) {
            Icon icon = new Icon();;
            icon.setLatlong(new LatLng(result.getDouble("lat"), result.getDouble("lon")));
            icon.setType(new IconType(result.getString("icon_type")));
            icon.setEvaluation(new Evaluation(result.getString("evaluation")));
            icon.setImg(new Image(result.getString("image")));
            icon.setObj(result.getObject("object_id"));
            list.add(icon);
        }
        return list;
    }

    private static int getMaxId(Connection con) throws SQLException {
        Statement statement = con.createStatement();
        ResultSet result = statement.executeQuery("select max(id) from icons");
        if (result.next()) {
            return result.getInt(1);
        } else {
            return 0;
        }
    }
}