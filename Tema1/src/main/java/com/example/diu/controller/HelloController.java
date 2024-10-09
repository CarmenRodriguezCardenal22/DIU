package com.example.diu.controller;

import com.example.diu.Contador;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    @FXML
    private Label lbContador;
    @FXML
    private Button btPositivo;
    @FXML
    private Button btNegativo;
    @FXML
    private Button btCero;
    @FXML
    private TextField CajaTexto;
    @FXML
    private ProgressBar Barra;

    private IntegerProperty num = new SimpleIntegerProperty(0);

    public void set(IntegerProperty num){
        this.num=num;
    }
    public IntegerProperty get(){
        return num;
    }
    @FXML
    protected void mas() {
        num.set(num.get()+1);
        lbContador.setText(String.valueOf(num.getValue()));
    }
    @FXML
    protected void menos() {
        num.set(num.get()-1);
        lbContador.setText(String.valueOf(num.getValue()));
    }
    @FXML
    protected void cero() {
        num.set(0);
        lbContador.setText(String.valueOf(num.getValue()));
    }
   @FXML
    protected void texto() {
        num.set(Integer.parseInt(CajaTexto.getText()));
        lbContador.setText(String.valueOf(num.getValue()));
    }
    public IntegerProperty numProperty(){
        return num;
    }
    @FXML
    private void initialize() {
        num.addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number oldValue, Number newValue) {
                lbContador.setText(newValue.toString());
                cambiarBarra(newValue.intValue());
            }
        });
    }

    private void cambiarBarra(int n){
        double progreso= ((double) n/50.0);
        Barra.setProgress(progreso);
    }

}