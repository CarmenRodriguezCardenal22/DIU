package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Reserva;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ReservasOverviewController {
    HotelModelo hotelModelo;

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    @FXML
    private TableView<Reserva> tabla;
    @FXML
    private TableColumn<Reserva, String> codColumn;
    @FXML
    private TableColumn<Reserva, String> entradaColumn;

    @FXML
    private Label cod;
    @FXML
    private Label llegada;
    @FXML
    private Label salida;
    @FXML
    private Label numHabit;
    @FXML
    private Label tipo;
    @FXML
    private Label fumador;
    @FXML
    private Label regimen;
    @FXML
    private Button nuevo, editar, borrar;

    private MainApp mainApp=new MainApp();

    public ReservasOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        codColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        entradaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaLlegadaProperty().asString());
        showReservasDetails(null);

        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservasDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tabla.setItems(mainApp.getReservaData());
    }
    @FXML
    private void handleDeleteReserva() throws ExcepcionHotel {
        int selectedIndex = tabla.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            Integer id=tabla.getItems().get(selectedIndex).getId();
            tabla.getItems().remove(selectedIndex);
            hotelModelo.deleteReserva(id);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    private void showReservasDetails(Reserva reserva) {
        if (reserva != null) {
            cod.setText(reserva.getId().toString());
            llegada.setText(reserva.getFechaLlegada().toString());
            salida.setText(reserva.getFechaSalida().toString());
            numHabit.setText(reserva.getNumHabitaciones().toString());
            tipo.setText(reserva.getTipoHabitacion());
            fumador.setText(reserva.getFumador().toString());
            regimen.setText(reserva.getRegimen());
        } else {
            cod.setText("");
            llegada.setText("");
            salida.setText("");
            numHabit.setText("");
            tipo.setText("");
            fumador.setText("");
            regimen.setText("");
        }
    }
    @FXML
    private void handleNewReserva() throws ExcepcionHotel {
        Reserva tempReserva = new Reserva();
        boolean okClicked = mainApp.showReservaEditDialog(tempReserva);
        if (okClicked) {
            mainApp.getReservaData().add(tempReserva);
            hotelModelo.addReserva(tempReserva);
        }
    }

    @FXML
    private void handleEditReserva() throws ExcepcionHotel {
        Reserva selectedReserva = tabla.getSelectionModel().getSelectedItem();
        if (selectedReserva != null) {
            boolean okClicked = mainApp.showReservaEditDialog(selectedReserva);
            if (okClicked) {
                showReservasDetails(selectedReserva);
                hotelModelo.editReserva(selectedReserva);
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
}
