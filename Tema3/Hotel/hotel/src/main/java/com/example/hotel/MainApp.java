package com.example.hotel;

import com.example.hotel.modelo.HotelModelo;
import com.example.hotel.modelo.repository.impl.ClienteRepositoryImpl;
import com.example.hotel.modelo.repository.impl.ReservaRepositoryImpl;
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
    //private ObservableList<Person> personData = FXCollections.observableArrayList();

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

    /*public ObservableList<Person> getPersonData() {
        return personData;
    }*/
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
    /*public boolean showPersonEditDialog(Person person) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PersonEditDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the person into the controller.
            PersonEditDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }*/
    /*public void showBirthdayStatistics() {
        try {
            // Load the fxml file and create a new stage for the popup.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("BirthdayStatistics.fxml"));
            AnchorPane page = (AnchorPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Birthday Statistics");
            //dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the persons into the controller.
            BirthdayStatiticsController controller = loader.getController();
            controller.setPersonData(personData);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
