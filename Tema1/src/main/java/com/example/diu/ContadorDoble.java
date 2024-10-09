package com.example.diu;

import javafx.application.Application;
import javafx.stage.Stage;

public class ContadorDoble extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Contador contador1 = new Contador();
        Contador contador2 = new Contador();
        contador1.numProperty().bindBidirectional(contador2.numProperty());
        Stage stage1 = new Stage();
        Stage stage2 = new Stage();
        contador1.start(stage1);
        contador2.start(stage2);
    }
    public static void main(String[] args) {
        launch(args);
    }
}
