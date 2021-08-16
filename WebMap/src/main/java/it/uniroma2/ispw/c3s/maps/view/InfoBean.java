package it.uniroma2.ispw.c3s.maps.view;

public class InfoBean {
    private String city;
    private String road;
    private String nr;
    private String idR;
    private String type;
    private String evaluation;
    private String status;

    public InfoBean() {
        this.city = null;
        this.road = null;
        this.nr = null;
        this.idR = null;
        this.type = null;
        this.evaluation = null;
        this.status = null;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getNr() {
        return nr;
    }

    public void setNr(String nr) {
        this.nr = nr;
    }

    public String getIdR() {
        return idR;
    }

    public void setIdR(String idR) {
        this.idR = idR;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(String evaluation) {
        this.evaluation = evaluation;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
