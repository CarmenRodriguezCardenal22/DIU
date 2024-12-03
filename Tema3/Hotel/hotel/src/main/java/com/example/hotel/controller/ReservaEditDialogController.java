package com.example.hotel.controller;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReservaEditDialogController {
    HotelModelo hotelModelo;
    private DoubleProperty progreso = new SimpleDoubleProperty();

    public ReservaEditDialogController() {
    }

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    @FXML
    private DatePicker llegada;
    @FXML
    private DatePicker salida;
    @FXML
    private Spinner numHabit;
    @FXML
    private ChoiceBox tipo;
    @FXML
    private CheckBox fumador;
    @FXML
    private VBox regimen;
    @FXML
    private ToggleGroup elegir;
    @FXML
    private RadioButton desayuno, pMedia, pCompleta;
    @FXML
    private Button aceptar, cancelar, limpiar;


    private Stage dialogStage;
    private Reserva reserva;
    private Cliente cliente;
    private boolean okClicked = false;

    @FXML
    private void initialize() throws ExcepcionHotel {
        //ArrayList<ReservaVO> reservas = HotelModelo.obtenerReservas();
        /*cambiarBarra(clientes.size());
        progressBar.progressProperty().bindBidirectional(progreso);
        progressIndicator.progressProperty().bindBidirectional(progreso);*/
        /*elegir=new ToggleGroup();
        desayuno.setToggleGroup(elegir);
        pMedia.setToggleGroup(elegir);
        pCompleta.setToggleGroup(elegir);*/
        tipo.setItems(FXCollections.observableArrayList("Doble individual", "Doble", "Junior Suite", "Suite"));

    }

    public void fumador(){
        if(fumador.isSelected()){
            reserva.setFumador(1);
        }
        else {
            reserva.setFumador(0);
        }
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

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        llegada.setValue(reserva.getFechaLlegada());
        salida.setValue(reserva.getFechaSalida());
        if (numHabit.getValueFactory() != null) {
            numHabit.getValueFactory().setValue(reserva.getNumHabitaciones());
        }
        tipo.setValue(reserva.getTipoHabitacion());
        fumador.setSelected(reserva.getFumador() == 1);
        regimen.getChildren().clear();

        elegir = new ToggleGroup();
        for (String opcion : reserva.getRegimenOpciones()) {
            RadioButton radioButton = new RadioButton(opcion);
            radioButton.setToggleGroup(elegir);
            if (opcion.equals(reserva.getRegimenSeleccionado())) {
                radioButton.setSelected(true);
            }
            regimen.getChildren().add(radioButton);
        }
        elegir.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                RadioButton selectedRadioButton = (RadioButton) newValue;
                reserva.setRegimenInsertado(selectedRadioButton.getText());
            }
        });

    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (isInputValid()) {
            reserva.setFechaLlegada(llegada.getValue());
            reserva.setFechaSalida(salida.getValue());
            if (numHabit.getValue() != null) {
                reserva.setNumHabitaciones((Integer) numHabit.getValue());
            }
            reserva.setTipoHabitacion((String) tipo.getValue());
            reserva.setFumador(fumador.isSelected() ? 1 : 0);
            if(elegir.getSelectedToggle() != null){
                RadioButton selectedRadioButton = (RadioButton) elegir.getSelectedToggle();
                System.out.println(selectedRadioButton.getText());
                reserva.setRegimenInsertado(selectedRadioButton.getText());
            }
            else{
                reserva.setRegimenInsertado("ninguno");
            }

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

        if (llegada.getValue() == null) {
            errorMessage += "Fecha de llegada no válida!\n";
        }
        if (salida.getValue() == null) {
            errorMessage += "Fecha de salida no válida!\n";
        }
        if (numHabit.getValue() == null || (Integer) numHabit.getValue() <= 0) {
            errorMessage += "Número de habitaciones debe ser mayor a 0!\n";
        }
        if (tipo.getValue() == null || tipo.getValue().toString().isEmpty()) {
            errorMessage += "Tipo de habitación no seleccionado!\n";
        }
        boolean isRegimenSeleccionado = elegir.getSelectedToggle() != null;

        if (!isRegimenSeleccionado) {
            errorMessage += "No valid regimen selected!\n";
        }

        if (errorMessage.isEmpty()) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Datos inválidos");
            alert.setHeaderText("Por favor corrige los campos inválidos");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
