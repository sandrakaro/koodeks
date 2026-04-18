module com.example.koodeks2 {
        requires javafx.controls;
        requires javafx.fxml;
        //requires org.junit.jupiter.api;

        opens com.example.koodeks2 to javafx.fxml;
        exports com.example.koodeks2;
        }