package com.example.tema2.controller;

import com.example.tema2.MainApp;
import javafx.fxml.FXML;

public class RootLayoutController {
    private MainApp mainApp=new MainApp();
    @FXML
    private void handleShowBirthdayStatistics() {
        mainApp.showBirthdayStatistics();
    }
}
