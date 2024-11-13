package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ClienteOverviewController {
    HotelModelo hotelModelo;

    public void setHotelModelo(HotelModelo hotelModelo) {
        this.hotelModelo = hotelModelo;
    }

    @FXML
    private TableView<Cliente> tabla;
    @FXML
    private TableColumn<Cliente, String> nombreColumn;
    @FXML
    private TableColumn<Cliente, String> apellidosColumn;

    @FXML
    private Label id;
    @FXML
    private Label nombre;
    @FXML
    private Label apellidos;
    @FXML
    private Label direccion;
    @FXML
    private Label ciudad;
    @FXML
    private Label provincia;

    private MainApp mainApp=new MainApp();

    public ClienteOverviewController() {
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        showPersonDetails(null);

        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPersonDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tabla.setItems(mainApp.getClienteData());
    }
    private void showPersonDetails(Cliente cliente) {
        if (cliente != null) {
            id.setText(cliente.getDni());
            nombre.setText(cliente.getFirstName());
            apellidos.setText(cliente.getLastName());
            direccion.setText(cliente.getStreet());
            ciudad.setText(cliente.getCity());
            provincia.setText(cliente.getProvincia());
        } else {
            id.setText("");
            nombre.setText("");
            apellidos.setText("");
            direccion.setText("");
            ciudad.setText("");
            provincia.setText("");
        }
    }
    @FXML
    private void handleDeleteCliente() throws ExcepcionHotel {
        int selectedIndex = tabla.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            String id=tabla.getItems().get(selectedIndex).getDni();
            tabla.getItems().remove(selectedIndex);
            hotelModelo.deleteCliente(id);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    @FXML
    private void handleNewCliente() throws ExcepcionHotel {
        Cliente tempCliente = new Cliente();
        boolean okClicked = mainApp.showClienteEditDialog(tempCliente);
        if (okClicked) {
            mainApp.getClienteData().add(tempCliente);
            hotelModelo.addCliente(tempCliente);
        }
    }

    @FXML
    private void handleEditCliente() throws ExcepcionHotel {
        Cliente selectedCliente = tabla.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showClienteEditDialog(selectedCliente);
            if (okClicked) {
                showPersonDetails(selectedCliente);
                hotelModelo.editCliente(selectedCliente);
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