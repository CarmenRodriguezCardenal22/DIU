package com.example.hotel;

import com.example.hotel.controller.ClienteEditDialogController;
import com.example.hotel.controller.ClienteOverviewController;
import com.example.hotel.controller.ReservaEditDialogController;
import com.example.hotel.controller.ReservasOverviewController;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.hotel.modelo.repository.impl.ReservaRepositoryImpl;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    HotelModelo hotelModelo = new HotelModelo();

    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    private ObservableList<Reserva> reservaData = FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel");

        initRootLayout();
        showClienteOverview();

         //this.primaryStage.getIcons().add(new Image("file:resources/images/icono32.png"));
        // Image imagen = new Image("file:resources/imagenes/icono32.png", 50, 50, true, true);
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("FXML Loader Error: " + e.getMessage());
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public MainApp() {
        try {
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
            hotelModelo.setClienteRepository(clienteRepository);
            hotelModelo.setReservaRepository(reservaRepository);

            //System.out.println(hotelModelo.obtenerClientes());
            //System.out.println(hotelModelo.obtenerReservas());
            clienteData.addAll(hotelModelo.mostrarClientes());
            reservaData.addAll(hotelModelo.mostrarReserva());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }
    public ObservableList<Reserva> getReservaData() {
        return reservaData;
    }

    public ObservableList<Reserva> getReservaData(Cliente cliente) {
        ObservableList<Reserva> reservas = FXCollections.observableArrayList();
        for(Reserva reserva : reservaData){
            if(reserva.getDniCliente().equals(cliente.getDni())){
                reservas.add(reserva);
            }
        }
        return reservas;
    }

    public static void main(String[] args) {
        launch(args);
    }
    public void showClienteOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClientesOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            ClienteOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setHotelModelo(hotelModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean showReservasOverview(Cliente cliente) throws ExcepcionHotel {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ReservasOverview.fxml"));
            AnchorPane reservaOverview = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(reservaOverview);
            dialogStage.setScene(scene);

            ReservasOverviewController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            //ArrayList<Reserva> lista=hotelModelo.mostrarReserva();
            controller.setReserva(cliente);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showClienteEditDialog(Cliente cliente) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            // dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ClienteEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setCliente(cliente);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean showReservaEditDialog(Reserva reserva) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ReservaEditDialog.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Reserva");
            // dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            ReservaEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setReserva(reserva);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void showOcupationStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("OcupationStatistics.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estadísticas de Ocupación");
            // dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // OcupationStatisticsController controller = loader.getController();
            // controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
