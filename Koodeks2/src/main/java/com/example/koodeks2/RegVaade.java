package com.example.koodeks2;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class RegVaade {
    private Stage stseen;

    public RegVaade (Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {
        Label label = new Label("Siin toimub registreerimine");

        VBox root = new VBox(label);

        stseen.setScene(new Scene(root, 800, 600));
        stseen.setTitle("Registrerimine");
        stseen.show();

    }
}
