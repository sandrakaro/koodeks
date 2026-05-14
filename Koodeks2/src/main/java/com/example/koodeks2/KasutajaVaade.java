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

public class KasutajaVaade {
    private Stage stseen;

    public KasutajaVaade(Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {

        Label pealkiri = new Label("K O O D E K S");
        Stiil.pealkirjaStiil(pealkiri);

        Label kirjeldus = new Label(" SINU ABILINE ARVUSÜSTEEMIDE TEISENDAMISEL");
        Stiil.kirjelduseStiil(kirjeldus);

        TextField kasutajaNimi = new TextField();
        kasutajaNimi.setPromptText("Kasutajanimi");
        kasutajaNimi.setPrefSize(200, 50);
        kasutajaNimi.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        PasswordField parool = new PasswordField();
        parool.setPromptText("Parool");
        parool.setPrefSize(200, 50);
        parool.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));

        Label error = new Label();
        Stiil.kirjelduseStiil(error);

        Button sisse = new Button("Logi sisse");
        Button tagasi = new Button("\u2190");

        Stiil.nuppudeStiil(sisse);
        Stiil.nupuTagasiStiil(tagasi);

        // -------

        BorderPane juur = new BorderPane();

        // pealkirja kirjelduse paigutus
        VBox topBox = new VBox(10, pealkiri, kirjeldus);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 50;");

        // tagasinupu paigutus
        VBox lefttopBox = new VBox(tagasi);
        lefttopBox.setAlignment(Pos.TOP_LEFT);
        lefttopBox.setStyle("-fx-padding: 10");

        // pealkiri, kirjeldus ja tagasinupp koos yleval
        StackPane topStack = new StackPane();
        topStack.getChildren().addAll(topBox, lefttopBox);

        // kasutajanime ja parooli valjad ning nupp sisseloogimiseks yhes reas
        HBox centerBox = new HBox(100, kasutajaNimi, parool, sisse);
        centerBox.setAlignment(Pos.CENTER);

        // centerBox ja viga "vale kasutajanimi..." koos (viga yleval)
        VBox mainCenter = new VBox(10, error, centerBox);
        mainCenter.setAlignment(Pos.CENTER);

        juur.setTop(topStack);
        juur.setCenter(mainCenter);

        Stiil.lisaTaust(juur);

        // sisseloogimine
        sisse.setOnAction(e -> {
            String nimi = kasutajaNimi.getText().trim();
            String p = parool.getText();

            if (nimi.isEmpty() || p.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "Täida kõik väljad!");
                alert.showAndWait();
                return;
            }

            Kasutaja kasutaja = null;

            try {
                kasutaja = Kasutaja.loeFailist(kasutajaNimi.getText(), parool.getText());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

            if (kasutaja != null) {
                new KoodVaade(stseen, kasutajaNimi.getText(), kasutaja.getViimaneTeisendus(),kasutaja).show();
            } else {
                error.setText("VALE KASUTAJANIMI VÕI PAROOL");
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

        stseen.setTitle("Sisseloogimine");
        stseen.show();

    }
}
