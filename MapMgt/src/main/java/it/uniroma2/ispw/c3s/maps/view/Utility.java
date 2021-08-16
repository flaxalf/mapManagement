package it.uniroma2.ispw.c3s.maps.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class Utility {

    public static void showAlert(String message, Alert.AlertType type){
        Alert alert = new Alert(type);
        alert.setTitle(null);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static void loadScene(URL location, ResourceBundle rb, ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(location, rb);
        Scene scene = new Scene(parent, 400, 600);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static boolean checkInt(String s) {
        if (Pattern.matches("[0-9]+", s) || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkAlpha(String s) {
        if (Pattern.matches("[a-zA-Z]+", s) || Pattern.matches("[a-zA-Z]+\\s+[a-zA-Z]+",s) || s.isEmpty()) {
            return true;
        } else {
            return false;
        }
    }
}
