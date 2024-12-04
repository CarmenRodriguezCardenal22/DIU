package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.utilidad.ReservaUtil;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;


public class ReservasOverviewController {
    HotelModelo hotelModelo=new HotelModelo();
    private MainApp mainApp=new MainApp();
    private Cliente cliente;
    private Reserva reserva;
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
    private Label dniR;
    @FXML
    private Label nombreR;

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
    private TextField buscarIdField;

    @FXML
    private Button nuevo, editar, borrar;



    public ReservasOverviewController() {
    }

    @FXML
    private void initialize() {
        codColumn.setCellValueFactory(cellData -> cellData.getValue().idProperty().asString());
        entradaColumn.setCellValueFactory(cellData -> cellData.getValue().fechaLlegadaProperty().asString());
        showReservasDetails(null);

        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showReservasDetails(newValue));

        // Listener para el buscador por ID
        buscarIdField.textProperty().addListener((observable, oldValue, newValue) -> buscarReservaPorId(newValue));
    }
    private boolean okClicked = false;
    public boolean isOkClicked() {
        return okClicked;
    }

    private Stage dialogStage;
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setReserva(Cliente cliente) {
        this.cliente=cliente;
        dniR.setText(cliente.getDni());
        nombreR.setText(cliente.getFirstName());
        tabla.setItems(mainApp.getReservaData(cliente));
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
        tempReserva.setDniCliente(dniR.getText());
        boolean okClicked = mainApp.showReservaEditDialog(tempReserva);
        if (okClicked) {
            hotelModelo.addReserva(tempReserva);
            ArrayList<Reserva> listaReservas = ReservaUtil.pasarReservaLista(hotelModelo.obtenerReservas());
            ObservableList<Reserva> reservas = FXCollections.observableArrayList(listaReservas);
            ObservableList<Reserva> reservasObservable = FXCollections.observableArrayList();

            for(Reserva reserva : reservas) {
                if(reserva.getDniCliente().equals(dniR.getText())) {
                    reservasObservable.add(reserva);
                }
            }
            tabla.setItems(reservasObservable);
            tabla.sort();
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
    private void buscarReservaPorId(String idTexto) {
        try {
            if (idTexto == null || idTexto.trim().isEmpty()) {
                // Si el campo está vacío, muestra detalles en blanco
                showReservasDetails(null);
                return;
            }

            Integer id = Integer.parseInt(idTexto);

            // Busca la reserva en los datos
            Reserva reservaEncontrada = null;
            for (Reserva reserva : mainApp.getReservaData(cliente)) {
                if (reserva.getId().equals(id)) {
                    reservaEncontrada = reserva;
                    break;
                }
            }

            // Muestra los detalles o los limpia si no se encuentra
            if (reservaEncontrada != null) {
                tabla.getSelectionModel().select(reservaEncontrada);
                showReservasDetails(reservaEncontrada);
            } else {
                showReservasDetails(null); // Limpia los detalles si no hay coincidencia
            }
        } catch (NumberFormatException e) {
            // Limpia los detalles si el ID ingresado no es válido
            showReservasDetails(null);
        }
    }

}
