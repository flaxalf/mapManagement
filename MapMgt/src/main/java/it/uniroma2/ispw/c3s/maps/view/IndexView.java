package it.uniroma2.ispw.c3s.maps.view;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;

import java.io.IOException;

public class IndexView {

    public void iconManager(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/IconManager.fxml"), null, event);
        } catch(IOException e){
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }

    public void validateAddress(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/ValidateAddress.fxml"), null, event);
        } catch(IOException e){
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }

    public void showIcon(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/ShowIcon.fxml"), null, event);
        } catch(IOException e){
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }

    public void showIcons(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/ShowIcons.fxml"), null, event);
        } catch(IOException e){
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }

    public void highlightIconsByZone(ActionEvent event) {
        try {
            Utility.loadScene(getClass().getResource("/it/uniroma2/ispw/c3s/maps/HighlightIconsByZone.fxml"), null, event);
        } catch(IOException e){
            Utility.showAlert("Errore: file corrotti!", Alert.AlertType.ERROR);
        }
    }
}
