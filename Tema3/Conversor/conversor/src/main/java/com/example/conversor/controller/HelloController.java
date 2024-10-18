package com.example.conversor.controller;

import Modelo.ExcepcionMoneda;
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
    public float guardarEuros() throws ExcepcionMoneda {
        return Float.valueOf(euros.getText());
    }
    private ConversorModelo conversorModelo;
    public void setConversorModelo(ConversorModelo conversorModelo) {
        this.conversorModelo = conversorModelo;
    }
    public void convertirMoneda() throws ExcepcionMoneda {
        double dolar= conversorModelo.conversion(guardarEuros());
        dolares.setText(String.valueOf(dolar));

    }
}