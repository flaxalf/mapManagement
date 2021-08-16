package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.controller.GeocodingAsyncTask;
import it.uniroma2.ispw.c3s.maps.model.LatLng;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ValidateAddressView {

    @FXML private TextField city_txt;
    @FXML private TextField road_txt;
    @FXML private TextField nr_txt;
    @FXML private TextField cap_txt;
    @FXML private Label check_city_txt;
    @FXML private Label check_road_nr_txt;
    @FXML private Label check_cap_txt;
    @FXML private Label result_txt;

    public void submit(ActionEvent event) {
        boolean flag = true;
        String city = city_txt.getText();
        String road = road_txt.getText();
        String nr = nr_txt.getText();
        String cap = cap_txt.getText();
        String address = new String();

        if (!Utility.checkAlpha(road)) {
            check_road_nr_txt.setText("Valori non validi!");
            flag = false;
        } else {
            check_road_nr_txt.setText("");
            if (!road.isEmpty()) {
                address = road + " ";
            }
        }
        if (!Utility.checkInt(nr)) {
            check_road_nr_txt.setText("Valori non validi!");
            flag = false;
        } else if (!nr.isEmpty() && (road.isEmpty() || !Utility.checkAlpha(road))) {
            check_road_nr_txt.setText("Inserire via!");
            flag = false;
        } else {
            check_road_nr_txt.setText("");
            if (!nr.isEmpty()) {
                address = address + nr + " ";
            }
        }
        if (!Utility.checkAlpha(city)) {
            check_city_txt.setText("Valore non valido!");
            flag = false;
        } else if (city.isEmpty()) {
            check_city_txt.setText("Valore obbligatorio!");
            flag = false;
        } else {
            check_city_txt.setText("");
            address = address + city;
        }
        if (!Utility.checkInt(cap)) {
            check_cap_txt.setText("Valore non valido!");
            flag = false;
        } else {
            check_cap_txt.setText("");
            if (!cap.isEmpty()) {
                address = address + " " + cap;
            }
        }
        if (flag) {
            GeocodingAsyncTask gat = new GeocodingAsyncTask(address);
            gat.start();
            // wait at most 3 secs for the answer
            try {
                gat.join(3000);
            } catch (InterruptedException e) {
                Utility.showAlert("Errore di sistema!", Alert.AlertType.ERROR);
                return;
            }
            LatLng latLng = gat.getLatLng();

            if (latLng != null)
                result_txt.setText(String.format("Le coordinate dell'indirizzo sono: %.4f | %.4f",
                        latLng.getLatitude(), latLng.getLongitude()));
            else
                result_txt.setText("Indirizzo inesistente o connessione scaduta!");
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
