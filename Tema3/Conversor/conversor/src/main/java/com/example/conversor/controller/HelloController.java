package com.example.conversor.controller;

import com.example.conversor.modelo.ConversorModelo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    private TextField euros;
    @FXML
    private TextField dolares;

    @FXML
    protected float guardarEuros() {
        return Float.valueOf(euros.getText());
    }
}