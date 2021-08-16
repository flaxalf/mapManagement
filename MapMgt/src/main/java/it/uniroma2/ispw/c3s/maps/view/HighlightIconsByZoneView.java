package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.controller.ValidateAddressController;
import it.uniroma2.ispw.c3s.maps.controller.HighlightIconsByZoneController;
import it.uniroma2.ispw.c3s.maps.model.LatLng;
import it.uniroma2.ispw.c3s.maps.model.Map;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class HighlightIconsByZoneView implements Initializable{

    @FXML private ChoiceBox cb_type;
    @FXML private TextField city_txt;
    @FXML private TextField road_txt;
    @FXML private TextField nr_txt;
    @FXML private TextField cap_txt;
    @FXML private Label check_city_txt;
    @FXML private Label check_road_nr_txt;
    @FXML private Label check_cap_txt;

    public void search(ActionEvent event) {
       boolean flag = true;
       String city = city_txt.getText();
       String road = road_txt.getText();
       String nr = nr_txt.getText();
       String cap = cap_txt.getText();
       String type = cb_type.getValue().toString().toLowerCase();
       String address = new String();

       if (!Utility.checkAlpha(road)) {
           check_road_nr_txt.setText("Valori non validi!");
           flag = false;
       } else {
           check_road_nr_txt.setText("");
           if (!road.isEmpty()) {
               address ="via " + road + " ";
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
           try {
               LatLng center = ValidateAddressController.geocodeAddress(address);
               if (center != null) {
                   Map mv = HighlightIconsByZoneController.highlightIconsByZone(center, type);
                   MapBundle mb = new MapBundle(mv);
                   Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Map.fxml"), mb, event);
               } else {
                   Utility.showAlert("Indirizzo non trovato, controllare i dati!", Alert.AlertType.WARNING);
               }
           } catch(ClassNotFoundException e) {
               Utility.showAlert("JDBC driver non trovato!", Alert.AlertType.ERROR);
           } catch (SQLException e) {
               Utility.showAlert("Errore nella comunicazione con il database!", Alert.AlertType.ERROR);
           } catch (IOException e) {
               Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
           }
       }
    }

    public void goBack(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Index.fxml"), null, event);
        } catch (IOException e) {
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cb_type.getItems().addAll("Appartamento", "Negozio", "Loft");
        cb_type.setValue("Appartamento");
    }

}
