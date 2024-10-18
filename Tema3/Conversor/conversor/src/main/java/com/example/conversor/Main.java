package com.example.conversor;

import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.HelloController;
import com.example.conversor.modelo.ConversorModelo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConexionJDBC conexion = new ConexionJDBC();
        Connection con=null;
        try {
            con=conexion.conectarBD();
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            ConversorModelo conversorModelo = new ConversorModelo();
            conversorModelo.setMonedaRepository(monedarepositoryImpl);

            /*MonedaVO monedaPrueba = new MonedaVO("dolar", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);*/
            conexion.desconectarBD(con);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void start(Stage escenarioPrincipal) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("conversor.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 320, 240);
            escenarioPrincipal.setTitle("Hello!");
            escenarioPrincipal.setScene(scene);
            escenarioPrincipal.show();

            HelloController controlador = fxmlLoader.getController();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}
