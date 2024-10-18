package com.example.conversor;

import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.HelloController;
import com.example.conversor.modelo.ConversorModelo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main extends Application {
    public static void main(String[] args) {launch(args);}
    @Override
    public void start(Stage escenarioPrincipal) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("conversor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            escenarioPrincipal.setTitle("Hello!");
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();

            HelloController controlador = fxmlLoader.getController();


            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            ConversorModelo conversorModelo = new ConversorModelo();
            conversorModelo.setMonedaRepository(monedarepositoryImpl);
            controlador.setConversorModelo(conversorModelo);
            /*MonedaVO monedaPrueba = new MonedaVO("dolar", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);*/

        }
        catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Base de datos desactivada.");
            alert.setContentText("No se ha podido encontrar la tasa de cambio.");

            alert.showAndWait();
        }
    }
}
