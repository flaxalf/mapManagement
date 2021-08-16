package it.uniroma2.ispw.c3s.maps.view;

import it.uniroma2.ispw.c3s.maps.controller.ShowIconsController;
import it.uniroma2.ispw.c3s.maps.model.Map;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Alert;

import java.io.IOException;


public class ShowIconsView {

    public void request(ActionEvent event) throws Exception {
        Node node = (Node) event.getSource();
        String data = (String) node.getUserData();
        int value = Integer.parseInt(data);

        Map mv = ShowIconsController.showIcons(value);
        MapBundle mb = new MapBundle(mv);

        Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Map.fxml"), mb, event);
    }

    public void goBack(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Index.fxml"), null, event);
        } catch (IOException e) {
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }
}
