package com.example.koodeks2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KoodVaade {

    private Stage stseen;

    public KoodVaade(Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {

        Label pealkiri = new Label("K O O D E K S");
        Stiil.pealkirjaStiil(pealkiri);

        BorderPane juur = new BorderPane();

        VBox topBox = new VBox(pealkiri);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 50;");

        juur.setTop(topBox);

        Stiil.lisaTaust(juur);

        stseen.setScene(new Scene(juur, 800, 600));
        stseen.setTitle("Kood");
        stseen.show();
    }

}
