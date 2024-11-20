package com.example.hotel.modelo.repository.impl;

import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.modelo.ExcepcionHotel;
import com.example.hotel.modelo.repository.ReservaRepository;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class ReservaRepositoryImpl implements ReservaRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ReservaVO> reserva;
    private ReservaVO reservaVO;

    public ReservaRepositoryImpl() {
    }

    public ArrayList<ReservaVO> ObtenerListaReserva(String dniCliente) throws ExcepcionHotel {
        ArrayList<ReservaVO> reservas = new ArrayList<>();
        String sql = "SELECT * FROM Reservas WHERE dniCliente = ?";

        try (Connection conn = this.conexion.conectarBD();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (conn == null) {
                throw new ExcepcionHotel("Conexión a la base de datos fallida");
            }

            stmt.setString(1, dniCliente);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Integer id = rs.getInt("id");
                    LocalDate fechaLlegada = rs.getDate("fechaLlegada").toLocalDate();
                    LocalDate fechaSalida = rs.getDate("fechaSalida").toLocalDate();
                    Integer numHabitaciones = rs.getInt("numeroHabitaciones");
                    String tipoHabitacion = rs.getString("tipoHabitacion");
                    Boolean fumador = rs.getBoolean("fumador");
                    String regimen = rs.getString("regimenAlojamiento");

                    ReservaVO reservaVO = new ReservaVO(id, fechaLlegada, fechaSalida, numHabitaciones, tipoHabitacion, fumador, regimen, dniCliente);
                    reservas.add(reservaVO);
                }
            }

        } catch (SQLException e) {
            throw new ExcepcionHotel("Error al obtener las reservas");
        }

        return reservas;
    }

    public void addReserva(ReservaVO m) throws ExcepcionHotel{
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Reservas (fechaLlegada, fechaSalida, numHabitaciones, tipoHabitacion, fumador, regimen, dniCliente) VALUES ('" + m.getFechaLlegada() + "','" + m.getFechaSalida() + "','"  + m.getNumHabitaciones() + "','" + m.getTipoHabitacion() + "','" + m.getFumador() + "','" + m.getRegimen() + "','" + m.getDniCliente() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void deleteReserva(Integer id) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Reservas WHERE id = %s", lastId());
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    public void editReserva(ReservaVO reservaVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Reservas SET fechaLlegada = '%s', fechaSalida = '%s', numHabitaciones = '%s', tipoHabitacion = '%s', fumador = '%s', regimen = '%s', dniCliente = '%s' WHERE id = %d", reservaVO.getFechaLlegada(), reservaVO.getFechaSalida(), reservaVO.getNumHabitaciones(), reservaVO.getTipoHabitacion(), reservaVO.getFumador(), reservaVO.getRegimen(), reservaVO.getDniCliente(), lastId());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExcepcionHotel {
        Integer lastReservaId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT id FROM Reservas ORDER BY id DESC LIMIT 1"); registro.next(); lastReservaId = registro.getInt("id")) {
            }

            return lastReservaId;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del Id");
        }
    }
}
