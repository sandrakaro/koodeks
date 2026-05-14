package com.example.koodeks2;

import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class RegVaade {
    private Stage stseen;

    public RegVaade (Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {
        Label pealkiri = new Label("K O O D E K S");
        Stiil.pealkirjaStiil(pealkiri);

        Label kirjeldus = new Label(" SINU ABILINE ARVUSÜSTEEMIDE TEISENDAMISEL");
        Stiil.kirjelduseStiil(kirjeldus);

        Label tekst = new Label("SISESTA UUS KASUTAJANIMI JA PAROOL");
        Stiil.kirjelduseStiil(tekst);

        TextField kasutajaNimi = new TextField();
        kasutajaNimi.setPromptText("Uus kasutajanimi");
        kasutajaNimi.setPrefSize(200, 50);
        kasutajaNimi.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        PasswordField parool = new PasswordField();
        parool.setPromptText("Uus parool");
        parool.setPrefSize(200, 50);
        parool.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        Button reg = new Button("Registreeru");
        Button tagasi = new Button("\u2190");

        Stiil.nuppudeStiil(reg);
        Stiil.nupuTagasiStiil(tagasi);

        // -----

        BorderPane juur = new BorderPane();

        // peakiri ja kirleldus yleval
        VBox topBox = new VBox(10, pealkiri, kirjeldus);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 50;");

        // tagasinupu paigutus
        VBox lefttopBox = new VBox(tagasi);
        lefttopBox.setAlignment(Pos.TOP_LEFT);
        lefttopBox.setStyle("-fx-padding: 10");

        // tagasinupp, pealkiri, kirjeldus koos yleval
        StackPane topStack = new StackPane();
        topStack.getChildren().addAll(topBox, lefttopBox);

        // kasutajanime ja parooli valjad, nupp registreerimiseks koos yhes reas
        HBox centerBox = new HBox(100, kasutajaNimi, parool, reg);
        centerBox.setAlignment(Pos.CENTER);

        // centerBox ja yleval tekst "sisesta uus..."
        VBox mainCenter = new VBox(20, tekst, centerBox);
        mainCenter.setAlignment(Pos.CENTER);

        juur.setTop(topStack);
        juur.setCenter(mainCenter);

        Stiil.lisaTaust(juur);

        // registreerimine
        reg.setOnAction(e -> {
            String nimi = kasutajaNimi.getText().trim();
            String p = parool.getText();

            if (nimi.isEmpty() || p.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Täida kõik väljad!");
                alert.showAndWait();
                return;
            }

            try {
                if (Kasutaja.kasutajaOnOlemas(nimi)) {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "See kasutajanimi on juba võetud!");
                    alert.showAndWait();
                } else {
                    Kasutaja uus = new Kasutaja(nimi, p);
                    uus.salvestaFaili();
                    System.out.println("Kasutaja loodud.");

                    new KasutajaVaade(stseen).show();
                }
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        // tagastab AlgneVaade lehekyljele
        tagasi.setOnAction(e -> {
            new AlgneVaade(stseen).show();
        });

        ScrollPane scrollPane = new ScrollPane(juur);
        scrollPane.setFitToWidth(true);
        scrollPane.setFitToHeight(true);

        stseen.getScene().setRoot(scrollPane);

        stseen.setTitle("Registrerimine");
        stseen.show();

    }
}
