package com.example.demo.controller;

import Modelo.ExcepcionMoneda;
import com.example.demo.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {
    private MainApp mainApp=new MainApp();
    @FXML
    private void handleImagen() {
        mainApp.showImagen();
    }
    public RootLayoutController() throws ExcepcionMoneda {
    }
}
