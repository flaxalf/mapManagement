package it.uniroma2.ispw.c3s.maps.view;

import java.io.IOException;
import java.net.URL;

import java.util.List;
import java.util.ResourceBundle;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.javascript.event.UIEventType;
import com.lynden.gmapsfx.javascript.object.*;
import it.uniroma2.ispw.c3s.maps.model.Icon;
import it.uniroma2.ispw.c3s.maps.model.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

public class MapView implements Initializable {
    @FXML
    private GoogleMapView googleMapView;
    private GoogleMap map;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /* ********* get map parameters from resourcebundle ******** */
        Map map = (Map) rb.getObject("map");

        googleMapView.setKey("AIzaSyAEi2uPbwjnFTNQRpZ_bt4_1QbspH6sDVI");
        googleMapView.addMapInializedListener(() -> {
            MapOptions mapOptions = new MapOptions();

            mapOptions.center(new LatLong(map.getCenter().getLatitude(), map.getCenter().getLongitude()))
                    .mapType(MapTypeIdEnum.ROADMAP)
                    .streetViewControl(false)
                    .zoom(map.getZoom());

            this.map = googleMapView.createMap(mapOptions);
            addIconsOnMap(map.getIconList());
        });
    }

    private void addIconsOnMap(List<Icon> iconList) {
        for (Icon i : iconList) {
            //Add a marker to the map
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLong(i.getLatlong().getLatitude(), i.getLatlong().getLongitude()))
                    .visible(Boolean.TRUE)
                    .title("object_id: " + i.getObj())
                    .icon(i.getImg().getImgPath());

            Marker marker = new Marker(markerOptions);
            map.addMarker(marker);
            map.addUIEventHandler(marker, UIEventType.click, jsObject -> {
                InfoWindowOptions infoWindowOptions = new InfoWindowOptions();
                infoWindowOptions.content(String.format("<h2>%s</h2>", i.getType().getType().toUpperCase()) +
                        String.format("Location: %.4f | %.4f<br>", i.getLatlong().getLatitude(), i.getLatlong().getLongitude()) +
                        String.format("Evaluation: %s/5", i.getEvaluation().getEvaluation()));

                InfoWindow infoWindow = new InfoWindow(infoWindowOptions);
                infoWindow.open(map, marker);
            });
        }
    }

    public void goBack(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Index.fxml"), null, event);
        } catch (IOException e) {
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }
}