package com.example.hotel.modelo.repository.impl;

import javafx.scene.control.Alert;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionJDBC {
    public ConexionJDBC() {
    }

    public Connection conectarBD() throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hotel?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
            return conn;
        } catch (Exception var2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("ERROR");
            alert.setHeaderText("Base de datos desactivada.");
            alert.setContentText("No se ha podido encontrar la base de datos.");

            alert.showAndWait();

            throw new SQLException();
        }
    }

    public void desconectarBD(Connection conn) {
        try {
            conn.close();
        } catch (SQLException var3) {
            SQLException ex = var3;
            System.out.println("\n--- SQLException capturada ---\n");

            while (ex != null) {
                System.out.println("Mensaje:   " + ex.getMessage());
                System.out.println("SQLState:  " + ex.getSQLState());
                System.out.println("ErrorCode: " + ex.getErrorCode());
                ex = ex.getNextException();
                System.out.println("");
            }
        }
    }
}