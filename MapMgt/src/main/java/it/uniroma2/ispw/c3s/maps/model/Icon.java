package it.uniroma2.ispw.c3s.maps.model;

import it.uniroma2.ispw.c3s.maps.dao.IconCRUD;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Icon{
    private LatLng latlong;
    private IconId id;
    private IconType type;
    private Status status;
    private Evaluation evaluation;
    private Image imgPath;
    private Object obj;

    public Icon() {
        this.id = new IconId();
        this.latlong = null;
        this.type = null;
        this.status = null;
        this.evaluation = null;
        this.imgPath = null;
        this.obj = null;
    }

    public Icon(LatLng latlong, IconType type, Status status, Evaluation evaluation, Image img, Object o) {
        this.id = new IconId();
        this.latlong = latlong;
        this.type = type;
        this.status = status;
        this.evaluation = evaluation;
        this.imgPath =img;
        this.obj = o;
    }
    public Icon(LatLng latlong, IconType type, Status status, Evaluation evaluation, Object o) {
        this.id = new IconId();
        this.latlong = latlong;
        this.type = type;
        this.status = status;
        this.evaluation = evaluation;
        this.imgPath =new Image(status);
        this.obj = o;
    }
    public LatLng getLatlong() {
        return latlong;
    }
    public void setLatlong(LatLng latlong) {
        this.latlong = latlong;
    }
    public void setId(IconId id) {
        this.id = id;
    }
    public IconType getType() {
        return type;
    }
    public void setType(IconType type) {
        this.type = type;
    }
    public Status getStatus() {
        return status;
    }
    public Evaluation getEvaluation() {
        return evaluation;
    }
    public void setEvaluation(Evaluation evaluation) {
        this.evaluation = evaluation;
    }
    public Image getImg() {
        return imgPath;
    }
    public void setImg(Image imgPath) {
        this.imgPath = imgPath;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object o) {
        this.obj = o;
    }

    public boolean insertIntoDB(Connection con) throws SQLException {
        return IconCRUD.insertIcon(con,this);
    }
    public boolean removeFromDB(Connection con) throws SQLException {
        return IconCRUD.removeIcon(con, this);
    }
    public boolean updateIntoDB(Connection con, Icon newIcon) throws SQLException {
        return IconCRUD.updateIcon(con, this, newIcon);
    }
    public static List<Icon> findIconsFromDB(Connection con, IconType type) throws SQLException {
        return IconCRUD.findIcons(con, type);
    }
}
