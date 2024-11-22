package com.example.demo.controller;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import com.example.demo.MainApp;
import com.example.demo.modelo.MonedaModelo;
import com.example.demo.vista.Moneda;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class MonedaOverviewController {
    @FXML
    private TableView<Moneda> tabla;
    @FXML
    private TableColumn<Moneda, String> lista;
    @FXML
    private Label nombre;
    @FXML
    private TextField euros;
    @FXML
    private TextField moneda;
    @FXML
    private Button convertir;
    @FXML
    private Button eliminar;

    public MonedaOverviewController() throws ExcepcionMoneda {
    }

    @FXML
    public float guardarEuros() throws ExcepcionMoneda {
        return Float.valueOf(euros.getText());
    }
    private MainApp mainApp=new MainApp();
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tabla.setItems(mainApp.getMonedaData());
    }
    private MonedaModelo monedaModelo;
    public void setMonedaModelo(MonedaModelo monedaModelo) {
        this.monedaModelo = monedaModelo;
    }
    /*public void convertirMoneda() throws ExcepcionMoneda {
        double dolar= monedaModelo.conversion(guardarEuros());
        moneda.setText(String.valueOf(dolar));

    }
    private void showMonedaDetails(MonedaVO moneda) {
        if (moneda != null) {
            nombre.setText(moneda.getNombre());
        } else {
            nombre.setText("");
        }
    }*/
    @FXML
    private void handleDeleteMoneda() throws ExcepcionMoneda {
        int selectedIndex = tabla.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Integer id=tabla.getItems().get(selectedIndex).getCodigo();
            tabla.getItems().remove(selectedIndex);
            monedaModelo.deleteMoneda(id);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("Ninguna Moneda Seleccionada");
            alert.setContentText("Por favor seleccione una moneda en la tabla.");

            alert.showAndWait();
        }
    }
}
