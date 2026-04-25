package com.example.koodeks2;

import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stseen) {
        stseen.setWidth(535);
        stseen.setHeight(535);
        stseen.setResizable(true);

        new AlgneVaade(stseen).show();
    }
}
