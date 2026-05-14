package com.example.koodeks2;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class AlgneVaade {
    private Stage stseen;

    public AlgneVaade (Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {

        Label pealkiri = new Label("K O O D E K S");
        Stiil.pealkirjaStiil(pealkiri);

        Label kirjeldus = new Label(" SINU ABILINE ARVUSÜSTEEMIDE TEISENDAMISEL");
        Stiil.kirjelduseStiil(kirjeldus);

        Button sisseloogimine = new Button("Logi sisse");
        Button registreeru = new Button("Registreeru");

        Stiil.nuppudeStiil(sisseloogimine);
        Stiil.nuppudeStiil(registreeru);

        BorderPane juur = new BorderPane();

        VBox topBox = new VBox(10, pealkiri, kirjeldus);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 50;");

        HBox centerBox = new HBox(100, sisseloogimine, registreeru);
        centerBox.setAlignment(Pos.CENTER);

        juur.setTop(topBox);
        juur.setCenter(centerBox);

        Stiil.lisaTaust(juur);

        sisseloogimine.setOnAction(e -> {
            new KasutajaVaade(stseen).show();
        });

        registreeru.setOnAction(e -> {
            new RegVaade(stseen).show();
        });

        ScrollPane scrollPane = new ScrollPane(juur);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        stseen.getScene().setRoot(scrollPane);

        stseen.setTitle("KOODEKS");
        stseen.show();

    }
}
