package it.uniroma2.ispw.c3s.maps.model;

public class Image {
    private String imgPath;

    public Image(String imgPath) {
        this.imgPath = imgPath;
    }

    public Image(Status status) {
        switch (status.getStatus()){
            //in base allo status metto URL immagine
            case "disponibile":
                this.imgPath = "https://cdn3.iconfinder.com/data/icons/developperss/PNG/Green%20Ball.png";
                break;
            case "disponibile a breve":
                this.imgPath = "https://cdn3.iconfinder.com/data/icons/developperss/PNG/Yellow%20Ball.png";
                break;
            case "non disponibile":
                this.imgPath = "https://cdn3.iconfinder.com/data/icons/developperss/PNG/Red%20Ball.png";
                break;
            default:
                this.imgPath = null;
                break;
        }
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}

