package com.example.demo;

import Modelo.ExcepcionMoneda;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.demo.controller.MonedaOverviewController;
import com.example.demo.modelo.MonedaModelo;
import com.example.demo.vista.Moneda;
import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApp extends Application {
    private Stage primaryStage;
    private BorderPane rootLayout;
    MonedaModelo monedaModelo = new MonedaModelo();

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Conversor");

        initRootLayout();
        showMonedaOverview();
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

    private ObservableList<Moneda> monedaData = FXCollections.observableArrayList();

    public MainApp() throws ExcepcionMoneda {
        /*MonedaRepositoryImpl monedaRepository = new MonedaRepositoryImpl();
        monedaModelo.setMonedaRepository(monedaRepository);*/
        monedaData.add(new Moneda(1, "Yen", 1));
        monedaData.add(new Moneda(2, "Lira", 0.5F));

        //monedaData.addAll(monedaModelo.mostrarMonedas());
    }

    public ObservableList<Moneda> getMonedaData() {
        return monedaData;
    }

    public void showMonedaOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("MonedaOverview.fxml"));
            AnchorPane monedaOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(monedaOverview);

            MonedaOverviewController controller = loader.getController();
            controller.setMainApp(this);
            controller.setMonedaModelo(monedaModelo);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showImagen() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("ImagenOverview.fxml"));
            AnchorPane page = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("Número de monedas");
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
