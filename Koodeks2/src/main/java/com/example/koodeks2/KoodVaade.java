package com.example.koodeks2;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KoodVaade {

    private Stage stseen;

    public KoodVaade(Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {

        Label label = new Label("Siin toimub teisendamine");

        VBox root = new VBox(label);

        stseen.setScene(new Scene(root, 800, 600));
        stseen.setTitle("Kood");
        stseen.show();
    }

}
