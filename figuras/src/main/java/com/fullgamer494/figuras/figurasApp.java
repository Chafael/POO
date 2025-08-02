package com.fullgamer494.figuras;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class figurasApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/fullgamer494/figuras/figurasView.fxml"));
            Scene scene = new Scene(loader.load(), 500, 600);

            primaryStage.setTitle("Calculadora de Figuras Geométricas");
            primaryStage.setScene(scene);
            primaryStage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}