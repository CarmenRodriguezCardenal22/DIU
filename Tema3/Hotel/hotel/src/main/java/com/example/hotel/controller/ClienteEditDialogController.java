package com.example.hotel.controller;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class ClienteEditDialogController {
    HotelModelo hotelModelo;
    private DoubleProperty progreso = new SimpleDoubleProperty();

    public ClienteEditDialogController() {
    }

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }
    @FXML
    private TextField dni;
    @FXML
    private TextField nombre;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField direccion;
    @FXML
    private TextField ciudad;
    @FXML
    private TextField provincia;


    private Stage dialogStage;
    private Cliente cliente;
    private boolean okClicked = false;

    @FXML
    private void initialize() throws ExcepcionHotel {
        ArrayList<ClienteVO> clientes = HotelModelo.obtenerClientes();
        /*cambiarBarra(clientes.size());
        progressBar.progressProperty().bindBidirectional(progreso);
        progressIndicator.progressProperty().bindBidirectional(progreso);*/
    }

    /*private void cambiarBarra(int n) {
        progreso.set(n / 50.0);
    }

    public IntegerProperty numProperty() {
        return new SimpleIntegerProperty((int) (progreso.get() * 50));
    }*/

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;

        dni.setText(cliente.getDni());
        if(cliente.getDni() != null) {
            dni.setDisable(true);
        }
        nombre.setText(cliente.getFirstName());
        apellidos.setText(cliente.getLastName());
        direccion.setText(cliente.getStreet());
        ciudad.setText(cliente.getCity());
        provincia.setText(cliente.getProvincia());
    }

    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            cliente.setDni(dni.getText());
            cliente.setFirstName(nombre.getText());
            cliente.setLastName(apellidos.getText());
            cliente.setStreet(direccion.getText());
            cliente.setCity(ciudad.getText());
            cliente.setProvincia(provincia.getText());

            okClicked = true;
            dialogStage.close();
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";

        if (dni.getText() == null || dni.getText().length() == 0) {
            errorMessage += "No valid dni!\n";
        }
        if (nombre.getText() == null || nombre.getText().length() == 0) {
            errorMessage += "No valid first name!\n";
        }
        if (apellidos.getText() == null || apellidos.getText().length() == 0) {
            errorMessage += "No valid last name!\n";
        }
        if (direccion.getText() == null || direccion.getText().length() == 0) {
            errorMessage += "No valid street!\n";
        }
        if (ciudad.getText() == null || ciudad.getText().length() == 0) {
            errorMessage += "No valid city!\n";
        }
        if (provincia.getText() == null || provincia.getText().length() == 0) {
            errorMessage += "No valid provincia!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
            return false;
        }
    }
}
