module com.example.tema2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.tema2 to javafx.fxml;
    exports com.example.tema2;
    exports com.example.tema2.controller;
    opens com.example.tema2.controller to javafx.fxml;
    exports com.example.tema2.vista;
    opens com.example.tema2.vista to javafx.fxml;
    exports com.example.tema2.modelo;
    opens com.example.tema2.modelo to javafx.fxml;
}