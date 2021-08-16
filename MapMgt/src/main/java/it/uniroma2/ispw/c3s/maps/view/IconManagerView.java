package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.controller.ValidateAddressController;
import it.uniroma2.ispw.c3s.maps.controller.IconManagerController;
import it.uniroma2.ispw.c3s.maps.model.LatLng;
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

public class IconManagerView implements Initializable {

    @FXML private Label check_id_txt;
    @FXML private Label check_id2_txt;
    @FXML private TextField id_txt;
    @FXML private TextField id2_txt;
    @FXML private ChoiceBox cb_type;
    @FXML private ChoiceBox cb_stars;
    @FXML private ChoiceBox cb_status;
    @FXML private TextField city_txt;
    @FXML private TextField road_txt;
    @FXML private TextField nr_txt;
    @FXML private Label check_city_txt;
    @FXML private Label check_road_nr_txt;

    public void insert(ActionEvent event) {
        boolean flag = true;
        boolean res;
        IconManagerController controller;
        String address = null;

        String city = city_txt.getText();
        String road = road_txt.getText();
        String nr = nr_txt.getText();
        String id = id_txt.getText();

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
        if (!Utility.checkInt(id)) {
            check_id_txt.setText("Valore non valido!");
            flag = false;
        } else if (id.isEmpty()) {
            check_id_txt.setText("Valore obbligatorio!");
            flag = false;
        } else {
            check_id_txt.setText("");
        }
        if (flag) {
            try {
                LatLng latLong = ValidateAddressController.geocodeAddress(address);
                if (latLong != null) {
                    res = IconManagerController.insertIcon(latLong, cb_type.getValue().toString().toLowerCase(),
                            cb_status.getValue().toString().toLowerCase(), cb_stars.getValue().toString().toLowerCase(),
                            Integer.valueOf(id_txt.getText()));
                    if (res) {
                        Utility.showAlert("Inserimento avvenuto con successo!", Alert.AlertType.INFORMATION);
                    } else {
                        Utility.showAlert("Inserimento non avvenuto, controllare i dati inseriti!",
                                Alert.AlertType.WARNING);
                    }
                } else {
                    Utility.showAlert("Indirizzo non valido, controllare i dati inseriti!", Alert.AlertType.WARNING);
                }
            } catch(ClassNotFoundException e) {
                Utility.showAlert("JDBC driver non trovato!", Alert.AlertType.ERROR);
            } catch (SQLException e) {
                Utility.showAlert("Errore nella comunicazione con il database!", Alert.AlertType.ERROR);
            }
        }
    }

    public void update(ActionEvent event) {
        boolean flag = true;
        IconManagerController controller;
        String address = null;
        boolean res;
        String city = city_txt.getText();
        String road = road_txt.getText();
        String nr = nr_txt.getText();
        String id = id_txt.getText();

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
        if (!Utility.checkInt(id)) {
            check_id_txt.setText("Valore non valido!");
            flag = false;
        } else if (id.isEmpty()) {
            check_id_txt.setText("Valore obbligatorio!");
            flag = false;
        } else {
            check_id_txt.setText("");
        }
        if (flag) {
            try {
                LatLng latLong = ValidateAddressController.geocodeAddress(address);
                if (latLong != null) {
                    res = IconManagerController.updateIcon(latLong, cb_type.getValue().toString().toLowerCase(),
                            cb_status.getValue().toString().toLowerCase(), cb_stars.getValue().toString().toLowerCase(),
                            Integer.valueOf(id_txt.getText()));
                    if (res) {
                        Utility.showAlert("Aggiornamento avvenuto con successo!", Alert.AlertType.INFORMATION);
                    } else {
                        Utility.showAlert("Aggiornamento non avvenuto, controllare i dati inseriti!",
                                Alert.AlertType.WARNING);
                    }
                } else {
                    Utility.showAlert("Indirizzo non valido, controllare i dati inseriti!",
                            Alert.AlertType.WARNING);
                }
            } catch (ClassNotFoundException e) {
                Utility.showAlert("JDBC driver non trovato!", Alert.AlertType.ERROR);
            } catch (SQLException e) {
                Utility.showAlert("Errore nella comunicazione con il database!", Alert.AlertType.ERROR);
            }
        }
    }

    public void delete(ActionEvent event) {
        IconManagerController controller;
        boolean res;
        String id = id2_txt.getText();
        if (!Utility.checkInt(id) || id.isEmpty()) {
            check_id2_txt.setText("Valore non valido!");
        } else {
            check_id2_txt.setText("");
            try {
                res = IconManagerController.removeIcon(Integer.valueOf(id2_txt.getText()));
                if (res) {
                    Utility.showAlert("Rimozione avvenuta con successo!", Alert.AlertType.INFORMATION);
                } else {
                    Utility.showAlert("Rimozione non avvenuta, controllare i dati!", Alert.AlertType.WARNING);
                }
            } catch (ClassNotFoundException e) {
                Utility.showAlert("JDBC driver non trovato!", Alert.AlertType.ERROR);
            } catch (SQLException e) {
                Utility.showAlert("Errore nella comunicazione con il database!", Alert.AlertType.ERROR);
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
        cb_stars.getItems().addAll("5","4","3","2","1");
        cb_status.getItems().addAll("Disponibile","Disponibile a breve","Non disponibile");
        cb_type.setValue("Appartamento");
        cb_stars.setValue("5");
        cb_status.setValue("Disponibile");
    }
}
