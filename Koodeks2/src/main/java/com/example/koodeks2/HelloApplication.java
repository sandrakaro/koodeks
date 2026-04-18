package com.example.koodeks2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Group juur = new Group();
        Scene scene = new Scene(juur, 320, 240);
        stage.setTitle("Koodeks");
        stage.setScene(scene);
        stage.show();
    }
}
