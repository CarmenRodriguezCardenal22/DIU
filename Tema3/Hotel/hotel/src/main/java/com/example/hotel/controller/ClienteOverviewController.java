package com.example.hotel.controller;

import com.example.hotel.MainApp;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.vista.Cliente;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;

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

    @FXML
    private TextField buscarDniField;

    @FXML
    private Button nuevo, editar, borrar, consultar;

    private MainApp mainApp=new MainApp();

    public ClienteOverviewController() {
    }

    @FXML
    private void initialize() {
        // Inicializar las columnas de la tabla
        nombreColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        apellidosColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        showClienteDetails(null);

        // Listener para cambios en el campo de texto buscarDniField
        buscarDniField.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {  // Si se presiona Enter
                String dni = buscarDniField.getText();
                buscarPorDni(dni);  // Validar y buscar por DNI
            }
        });

        // Listener para cambios en la selección de la tabla
        tabla.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showClienteDetails(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;

        tabla.setItems(mainApp.getClienteData());
    }
    private void showClienteDetails(Cliente cliente) {
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
            String dni = tempCliente.getDni();

            // Validar el DNI solo cuando se haya introducido completamente
            if (dni == null || dni.trim().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("DNI Vacío");
                alert.setHeaderText("DNI no ingresado");
                alert.setContentText("Por favor, ingrese un DNI válido.");
                alert.showAndWait();
                return;
            }

            // Validar el formato del DNI
            if (!validarDni(dni)) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Formato inválido");
                alert.setHeaderText("DNI inválido");
                alert.setContentText("El DNI no es válido. Asegúrate de que el número se corresponde con la letra.");
                alert.showAndWait();
                return;
            }

            // Si el DNI es válido, agregar el cliente
            mainApp.getClienteData().add(tempCliente);
            hotelModelo.addCliente(tempCliente);
            tabla.sort();
        }
    }

    @FXML
    private void handleEditCliente() throws ExcepcionHotel {
        Cliente selectedCliente = tabla.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showClienteEditDialog(selectedCliente);
            if (okClicked) {
                showClienteDetails(selectedCliente);
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

    @FXML
    private void handleConsultarReservas() throws ExcepcionHotel {
        Cliente selectedCliente = tabla.getSelectionModel().getSelectedItem();
        if (selectedCliente != null) {
            boolean okClicked = mainApp.showReservasOverview(selectedCliente);
            if (okClicked) {
                showClienteDetails(selectedCliente);
                hotelModelo.obtenerReservas();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");

            alert.showAndWait();
        }
    }
    private void buscarPorDni(String dni) {
        if (dni == null || dni.trim().isEmpty()) {
            tabla.getSelectionModel().clearSelection(); // Limpiar selección
            showClienteDetails(null); // Limpiar detalles
            return;
        }

        // Validar el formato del DNI
        if (!validarDni(dni)) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Formato inválido");
            alert.setHeaderText("DNI inválido");
            alert.setContentText("Por favor, ingrese un DNI válido (número y letra deben coincidir).");
            alert.showAndWait();
            return;
        }

        // Buscar el cliente por DNI
        Cliente clienteEncontrado = null;
        for (Cliente cliente : mainApp.getClienteData()) { // Cambiado para buscar en los datos del cliente
            if (cliente.getDni().equalsIgnoreCase(dni.trim())) {
                clienteEncontrado = cliente;
                break;
            }
        }

        // Si se encuentra el cliente, seleccionarlo y mostrar los detalles
        if (clienteEncontrado != null) {
            tabla.getSelectionModel().select(clienteEncontrado); // Selecciona el cliente en la tabla
            tabla.scrollTo(clienteEncontrado); // Desplaza la tabla si es necesario
            showClienteDetails(clienteEncontrado); // Muestra los detalles
        } else {
            tabla.getSelectionModel().clearSelection(); // Limpiar selección si no se encuentra el cliente
            showClienteDetails(null); // Limpiar detalles
        }
    }

    private boolean validarDni(String dni) {
        // Asegurarnos de que el formato básico es correcto (8 dígitos y 1 letra)
        if (!dni.matches("\\d{8}[A-Za-z]")) {
            return false;
        }

        // Separar el número y la letra
        int numero = Integer.parseInt(dni.substring(0, 8));
        char letra = Character.toUpperCase(dni.charAt(8));

        // Array de letras según la tabla de módulo 23
        char[] letrasDni = {'T', 'R', 'W', 'A', 'G', 'M', 'Y', 'F', 'P', 'D', 'X', 'B', 'N', 'J', 'Z', 'S', 'Q', 'V', 'H', 'L', 'C', 'K', 'E'};

        // Comparar la letra calculada con la letra proporcionada
        return letra == letrasDni[numero % 23];
    }

}
