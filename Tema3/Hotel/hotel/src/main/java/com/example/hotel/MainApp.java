package com.example.hotel;

import com.example.hotel.controller.ClienteEditDialogController;
import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.hotel.modelo.repository.impl.ReservaRepositoryImpl;
import com.example.hotel.vista.Cliente;
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

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Hotel ");

        //initRootLayout();
        //showPersonOverview();

        //this.primaryStage.getIcons().add(new Image("file:resources/images/icono32.png"));
        //Image imagen = new Image("file:resources/imagenes/icono32.png", 50, 50, true, true);
    }
    /*public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }*/


    public MainApp() {
        try{
            ClienteRepositoryImpl clienteRepository = new ClienteRepositoryImpl();
            ReservaRepositoryImpl reservaRepository = new ReservaRepositoryImpl();
            hotelModelo.setClienteRepository(clienteRepository);
            hotelModelo.setReservaRepository(reservaRepository);
            System.out.println(hotelModelo.obtenerClientes());
            System.out.println(hotelModelo.obtenerReservas());
            //personData.addAll(agendaModelo.mostrarPersonas());

        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private ObservableList<Cliente> clienteData = FXCollections.observableArrayList();
    public ObservableList<Cliente> getClienteData() {
        return clienteData;
    }
    public static void main(String[] args) {
        launch(args);
    }
    /*public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonOverview.fxml"));
            AnchorPane personOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(personOverview);

            PersonOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setAgendaModelo(agendaModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
    public boolean showClienteEditDialog(Cliente cliente) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ClienteEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Cliente");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the cliente into the controller.
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
    public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("OcupationStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Estatidisticas de ocupacion");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
           /* OcupationStatiticsController controller = loader.getController();
            controller.setPersonData(personData);*/

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
