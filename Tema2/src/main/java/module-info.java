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

    opens com.example.tema2 to javafx.fxml;
    exports com.example.tema2;
    exports com.example.tema2.controller;
    opens com.example.tema2.controller to javafx.fxml;
}