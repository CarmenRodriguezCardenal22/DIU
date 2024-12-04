package com.example.hotel.controller;

import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.LocalDate;

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
    private Label mensajeFumador;
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
        tipo.setItems(FXCollections.observableArrayList("Doble individual", "Doble", "Junior Suite", "Suite"));

        llegada.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isBefore(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fecha no válida");
                alert.setHeaderText("Fecha de llegada no válida");
                alert.setContentText("La fecha de llegada no puede ser anterior al día actual.");
                alert.showAndWait();
                llegada.setValue(null);
            } else if (salida.getValue() != null && salida.getValue().isBefore(newValue)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Fecha no válida");
                alert.setHeaderText("Inconsistencia en las fechas");
                alert.setContentText("La fecha de salida no puede ser anterior a la fecha de llegada.");
                alert.showAndWait();
                salida.setValue(null); // Limpiar la fecha de salida incorrecta
            }
        });

        // Validación de fecha de salida
        salida.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                if (newValue.isBefore(LocalDate.now())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Fecha no válida");
                    alert.setHeaderText("Fecha de salida no válida");
                    alert.setContentText("La fecha de salida no puede ser anterior al día actual.");
                    alert.showAndWait();
                    salida.setValue(null);
                } else if (llegada.getValue() != null && newValue.isBefore(llegada.getValue())) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Fecha no válida");
                    alert.setHeaderText("Inconsistencia en las fechas");
                    alert.setContentText("La fecha de salida no puede ser anterior a la fecha de llegada.");
                    alert.showAndWait();
                    salida.setValue(null); // Limpiar la fecha de salida incorrecta
                }
            }
        });

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 1, 1);
        numHabit.setValueFactory(valueFactory);

        fumador.setOnAction(event -> {
            if (fumador.isSelected()) {
                mensajeFumador.setText("En virtud de la ley de sanidad se informa a los clientes de que solo podrán fumar en las habitaciones reservadas para tal fin.");
            } else {
                mensajeFumador.setText(""); // Limpiar el mensaje si se desmarca el CheckBox
            }
        });
    }

    public void fumador(){
        if(fumador.isSelected()){
            reserva.setFumador(1);
        }
        else {
            reserva.setFumador(0);
        }
    }

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
    @FXML
    private void handleLimpiar() {
        // Obtén las fechas de los DatePickers
        LocalDate fechaInicioValue = fechaInicio.getValue();
        LocalDate fechaFinalValue = fechaFinal.getValue();

        // Verifica que ambas fechas no sean nulas antes de compararlas
        if (fechaInicioValue != null && fechaFinalValue != null) {
            if (fechaInicioValue.isBefore(fechaFinalValue)) {
                // La fecha de inicio es antes que la fecha final
                System.out.println("La fecha de inicio es antes de la fecha final.");
            } else {
                // La fecha de inicio no es antes que la fecha final (puede ser igual o posterior)
                System.out.println("La fecha de inicio no es antes de la fecha final.");
            }
        } else {
            // Si alguna de las fechas es nula, maneja el caso adecuadamente
            if (fechaInicioValue == null) {
                System.out.println("La fecha de inicio no está seleccionada.");
            }
            if (fechaFinalValue == null) {
                System.out.println("La fecha final no está seleccionada.");
            }
        }

        // Limpiar las fechas de los DatePickers
        fechaInicio.setValue(null);
        fechaFinal.setValue(null);
    }
    }

}
