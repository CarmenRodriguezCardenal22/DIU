package com.example.hotel.controller;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
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
    private TextField dniR;
    @FXML
    private TextField nombreR;

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
    private Cliente cliente=new Cliente();
    private boolean okClicked = false;

    @FXML
    private void initialize() throws ExcepcionHotel {
        //ArrayList<ReservaVO> reservas = HotelModelo.obtenerReservas();
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

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;

        dniR.setText(cliente.getDni());
        nombreR.setText(cliente.getFirstName());

        llegada.setValue(reserva.getFechaLlegada());
        salida.setValue(reserva.getFechaSalida());
        if (numHabit.getValueFactory() != null) {
            numHabit.getValueFactory().setValue(reserva.getNumHabitaciones());
        }
        tipo.setValue(reserva.getTipoHabitacion());
        fumador.setSelected(reserva.getFumador());
        regimen.getChildren().clear();
        for (String opcion : reserva.getRegimenOpciones()) {
            CheckBox checkBox = new CheckBox(opcion);
            checkBox.setSelected(reserva.getRegimenSeleccionado().contains(opcion));
            regimen.getChildren().add(checkBox);
        }
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
            reserva.setFumador(fumador.isSelected());
            List<String> regimenSeleccionado = new ArrayList<>();
            for (Node node : regimen.getChildren()) {
                if (node instanceof CheckBox) {
                    CheckBox checkBox = (CheckBox) node;
                    if (checkBox.isSelected()) {
                        regimenSeleccionado.add(checkBox.getText());
                    }
                }
            }
            reserva.setRegimenSeleccionado(regimenSeleccionado);

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
        if (!fumador.isSelected() && fumador.isIndeterminate()) {
            errorMessage += "Estado de fumador no especificado!\n";
        }
        boolean isRegimenSeleccionado = false;
        for (Node node : regimen.getChildren()) {
            if (node instanceof CheckBox) {
                CheckBox checkBox = (CheckBox) node;
                if (checkBox.isSelected()) {
                    isRegimenSeleccionado = true;
                    break;
                }
            }
        }
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
