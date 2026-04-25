package com.example.koodeks2;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class Stiil {

    public static void lisaTaust(Pane paneel){
        paneel.setStyle("-fx-background-color: linear-gradient(to bottom, #e2cfe7, #d6e4f0)");
    }

    public static void pealkirjaStiil(Label pealkiri) {
        pealkiri.setTextFill(Color.web("#776f8f"));
        pealkiri.setFont(Font.font("Segoe UI", FontWeight.EXTRA_BOLD, 150));
    }

    public static void kirjelduseStiil(Label kirjeldus) {
        kirjeldus.setTextFill(Color.web("#504c59"));
        kirjeldus.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, 30));
    }

    public static void nuppudeStiil(Button nupp) {
        nupp.setPrefSize(200, 50);
        nupp.setFont(Font.font("Verdana", FontWeight.BOLD, 16));

        nupp.setStyle(
                "-fx-background-color: linear-gradient(to bottom right, #b06ab3, #4568dc);" +
                        "-fx-background-radius: 30; " +
                        "-fx-text-fill: white;" +
                        "-fx-cursor: hand;" +
                        "-fx-border-color: rgb(255, 255, 255, 0.3);" +
                        "-fx-border-radius: 30;" +
                        "-fx-border-width: 1;"
        );

        javafx.scene.effect.DropShadow sh = new javafx.scene.effect.DropShadow();
        sh.setColor(Color.rgb(0, 0, 0, 0.2));
        sh.setRadius(15);
        sh.setOffsetY(5);
        nupp.setEffect(sh);

        nupp.setOnMouseEntered(e -> nupp.setOpacity(0.9));
        nupp.setOnMouseExited(e -> nupp.setOpacity(1.0));
    }
}
