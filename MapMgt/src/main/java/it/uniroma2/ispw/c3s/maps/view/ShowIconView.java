package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.controller.ShowIconController;
import it.uniroma2.ispw.c3s.maps.model.Map;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import java.io.IOException;

public class ShowIconView {
    public void request(ActionEvent event) {
        Node node = (Node) event.getSource();
        String data = (String) node.getUserData();
        int value = Integer.parseInt(data);

        Map mv = ShowIconController.showIcon(value);
        MapBundle mb = new MapBundle(mv);

        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Map.fxml"), mb, event);
        } catch (IOException e) {
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
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
