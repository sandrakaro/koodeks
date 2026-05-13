package com.example.koodeks2;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stseen) {
        stseen.setMinWidth(800);
        stseen.setMinHeight(600);

        stseen.setScene(new Scene(new Pane(), 800, 600));

        new AlgneVaade(stseen).show();
    }
}
