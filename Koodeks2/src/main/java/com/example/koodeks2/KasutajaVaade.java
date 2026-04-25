package com.example.koodeks2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class KasutajaVaade {
    private Stage stseen;

    public KasutajaVaade(Stage stseen) {
        this.stseen = stseen;
    }

    public void show() {

        Label pealkiri = new Label("K O O D E K S");
        Stiil.pealkirjaStiil(pealkiri);

        Label kirjeldus = new Label(" sinu abiline arvusüsteemide teisendamisel");
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
        error.setFont(Font.font("Verdana", FontWeight.NORMAL, 20));
        error.setTextFill(Color.web("#776f8f"));

        Button sisse = new Button("Logi sisse");

        Stiil.nuppudeStiil(sisse);

        BorderPane juur = new BorderPane();

        VBox topBox = new VBox(10, pealkiri, kirjeldus);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 50;");

        HBox centerBox = new HBox(100, kasutajaNimi, parool, sisse);
        centerBox.setAlignment(Pos.CENTER);

        VBox bottomBox = new VBox(10, error);
        bottomBox.setAlignment(Pos.CENTER);
        bottomBox.setStyle("-fx-padding: 100");

        juur.setTop(topBox);
        juur.setCenter(centerBox);
        juur.setBottom(bottomBox);

        Stiil.lisaTaust(juur);

        sisse.setOnAction(e -> {
            Kasutaja kasutaja = Kasutaja.loeFailist(kasutajaNimi.getText(), parool.getText());

            if (kasutaja != null) {
                new KoodVaade(stseen).show();
            } else {
                error.setText("Vale kasutajanimi või parool");
            }
        });

        stseen.setScene(new Scene(juur, 800, 600));
        stseen.setTitle("Sisseloogimine");
        stseen.setMaximized(true);
        stseen.show();

    }
}
