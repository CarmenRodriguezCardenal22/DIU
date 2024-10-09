package com.example.diu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PanelDeDiseño extends Application {

    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            Pane raiz = new Pane();
            Button bt1, bt2, bt3;
            bt1 = new Button("Botón 1");
            bt1.setLayoutX(10);
            bt1.setLayoutY(20);
            bt2 = new Button("Botón 2");
            bt2.setLayoutX(200);
            bt2.setLayoutY(100);
            bt3 = new Button("Botón 3");
            bt3.setLayoutX(50);
            bt3.setLayoutY(150);
            raiz.getChildren().addAll(bt1, bt2, bt3);

            Scene escena = new Scene(raiz, 300, 200);
            escenarioPrincipal.setTitle("Panel");
            escenarioPrincipal.setScene(escena);
            escenarioPrincipal.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
