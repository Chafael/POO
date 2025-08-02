package com.fullgamer494.lectorcsv;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class lectorCSVApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/fullgamer494/lectorcsv/lectorCSV.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);

        scene.getStylesheets().add(getClass().getResource("/com/fullgamer494/lectorcsv/style.css").toExternalForm());

        stage.setTitle("Lector CSV - Aplicación Simple");
        stage.setScene(scene);
        stage.setMinWidth(500);
        stage.setMinHeight(400);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}