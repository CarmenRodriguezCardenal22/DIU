package com.example.conversor;

import Modelo.MonedaVO;
import Modelo.repository.impl.ConexionJDBC;
import Modelo.repository.impl.MonedaRepositoryImpl;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        ConexionJDBC conexion = new ConexionJDBC();
        Connection con=null;
        try {
            con=conexion.conectarBD();
            MonedaRepositoryImpl monedarepositoryImpl = new MonedaRepositoryImpl();
            MonedaVO monedaPrueba = new MonedaVO("dolar", 1.2F);
            monedarepositoryImpl.addMoneda(monedaPrueba);
            conexion.desconectarBD(con);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
