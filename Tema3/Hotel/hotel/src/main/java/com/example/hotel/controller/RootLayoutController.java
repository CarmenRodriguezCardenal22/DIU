package com.example.hotel.controller;

import com.example.hotel.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {
    private MainApp mainApp;
    @FXML
    private void handleOcupationStatistics() {
        mainApp.showOcupationStatistics();
    }
    public RootLayoutController(){
    }
}
