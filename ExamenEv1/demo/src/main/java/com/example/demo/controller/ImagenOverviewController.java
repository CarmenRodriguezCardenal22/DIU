package com.example.demo.controller;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import com.example.demo.modelo.MonedaModelo;
import com.example.demo.vista.Moneda;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;
import java.util.ArrayList;

public class ImagenOverviewController {
    private DoubleProperty progreso = new SimpleDoubleProperty();

    @FXML
    private Label numMonedas;

    @FXML
    private void initialize() throws ExcepcionMoneda {
        //ArrayList<MonedaVO> monedas = MonedaModelo.obtenerMonedas();
        ArrayList<MonedaVO> monedas = new ArrayList<>();
        contarMonedas(monedas.size());
        ponerFoto();
    }
    @FXML
    private void ponerFoto(){
        Image imagen = new Image("file:resources/imagenes/foto.jpg", 50, 50, true, true);
    }
    @FXML
    private void contarMonedas(int n){progreso.set(n); numMonedas.setText(String.valueOf(n));}

}
