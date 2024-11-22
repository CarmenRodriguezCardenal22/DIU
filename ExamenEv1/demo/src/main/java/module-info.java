module com.example.demo {
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
    requires AccesoBBDDExamenV2;
    requires java.desktop;
    requires jdk.compiler;

    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    opens com.example.demo.controller to javafx.fxml;
    exports com.example.demo.controller;
}