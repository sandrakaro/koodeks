package com.example.koodeks2;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class KoodVaade {

    private Stage stseen;
    private String kasutajaNimi;
    private String viimaneTeisendus;

    public KoodVaade(Stage stseen, String kasutajaNimi, String viimaneTeisendus) {
        this.stseen = stseen;
        this.kasutajaNimi = kasutajaNimi;
        this.viimaneTeisendus = viimaneTeisendus;
    }

    public void show() {

        Label pealkiri = new Label("K O O D E K S");
        Stiil.pealkirjaStiil(pealkiri);

        Label tere = new Label("TERE TULEMAST,  " + kasutajaNimi + " !\n" +
                "VIIMANE TEISENDUS:  " + viimaneTeisendus);
        Stiil.koodStiil(tere);

        Label kodeering = new Label(" 2  - BINAARKOOD\n" +
                " 8  - KAHEKSANDKOOD\n" +
                "10 - KÜMNENDKOOD\n" +
                "16 - KUUETEISTKÜMNENDKOOD");
        Stiil.koodStiil(kodeering);

        // sisestusväljad
        TextField arv = new TextField();
        TextField algneKod = new TextField();
        TextField soovKod = new TextField();

        // tulemuse jaoks
        Label tulemus = new Label("");
        Stiil.koodStiil(tulemus);

        // nupud
        Button teisendaNupp = new Button("TEISENDA");
        Stiil.nuppudeStiil(teisendaNupp);

        Button tagasi = new Button("\u2190");
        Stiil.nupuTagasiStiil(tagasi);


        // -----layout------

        BorderPane juur = new BorderPane();

        // pealkiri
        VBox topBox = new VBox(pealkiri);
        topBox.setAlignment(Pos.CENTER);
        topBox.setStyle("-fx-padding: 50;");

        // tagasi nupp
        VBox tagasiBox = new VBox(tagasi);
        tagasiBox.setAlignment(Pos.TOP_LEFT);
        tagasiBox.setStyle("-fx-padding: 10");

        // pealkiri ja nupp koos
        StackPane topStack = new StackPane();
        topStack.getChildren().addAll(topBox, tagasiBox);

        // tutvustus
        VBox tutvustusBox = new VBox(tere);
        tutvustusBox.setAlignment(Pos.TOP_LEFT);
        tutvustusBox.setPadding(new javafx.geometry.Insets(10, 0, 0, 40));

        // instruktsioon
        VBox instr = new VBox(kodeering);
        instr.setAlignment(Pos.TOP_RIGHT);
        instr.setPadding(new javafx.geometry.Insets(10, 20, 10, 10));

        // tutvustus ja instruktsioon koos yhes reas
        // spacer - taidab vaba ruumi
        HBox infoRida = new HBox();
        infoRida.setAlignment(Pos.CENTER);
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        HBox.setHgrow(spacer, Priority.ALWAYS); // et tutvustus ja instruktsioon oleksid yksteisest voimalikult kaugel boxi raames
        infoRida.getChildren().addAll(tutvustusBox, spacer, instr);

        // sisestusväljade paigutus
        VBox formBox = new VBox(25);
        formBox.setPadding(new javafx.geometry.Insets(20, 0, 0, 40));
        formBox.getChildren().addAll(
                looInputRida("ARV, MIDA SOOVID TEISENDADA:", arv),
                looInputRida("ALGNE KODEERING:", algneKod),
                looInputRida("TULEMUSE SOOVITUD KODEERING:", soovKod),
                looInputRida("TULEMUS:", tulemus)
        );

        // nupu paigutus
        HBox nuppBox = new HBox(teisendaNupp);
        nuppBox.setAlignment(Pos.BOTTOM_RIGHT);
        nuppBox.setPadding(new javafx.geometry.Insets(0, 180, 70, 0));

        // pealkiri, tutvustus ja instruktsioon koos (peaksid olema yleval)
        VBox topOsa = new VBox(topStack, infoRida);

        juur.setTop(topOsa);
        juur.setCenter(formBox);
        juur.setBottom(nuppBox);

        Stiil.lisaTaust(juur);

        // tagastab AlgneVaade lehekyljele
        tagasi.setOnAction(e -> {
            new AlgneVaade(stseen).show();
        });

        // teisendamine
        teisendaNupp.setOnAction(e -> {
            // midagi siin
            // tulemus.setText();
        });

        stseen.setScene(new Scene(juur, 800, 600));
        stseen.setTitle("Kood");
        stseen.show();
    }

    // sestusväljade paigutuse abimeetodi
    private HBox looInputRida(String tekst, javafx.scene.Node inputNode) {
        Label l = new Label(tekst);
        Stiil.koodStiil(l);
        HBox rida = new HBox(15, l, inputNode);
        rida.setAlignment(Pos.CENTER_LEFT);
        return rida;
    }
}
