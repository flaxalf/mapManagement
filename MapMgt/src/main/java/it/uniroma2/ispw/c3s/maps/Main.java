package it.uniroma2.ispw.c3s.maps;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/it/uniroma2/ispw/c3s/maps/Index.fxml"));
        Scene scene = new Scene(root, 400, 600);
        stage.setTitle("FERSA - Maps Subsystem");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
