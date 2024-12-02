package com.example.hotel.modelo.repository.impl;

import com.example.hotel.controller.ReservaEditDialogController;
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
    private ArrayList<ReservaVO> reservas;
    private ReservaVO reserva;

    public ReservaRepositoryImpl() {
    }

    public ArrayList<ReservaVO> ObtenerListaReserva() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.reservas=new ArrayList<>();
            this.stmt=conn.createStatement();
            this.sentencia="SELECT * FROM Reservas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer id = rs.getInt("id");
                LocalDate fechaLlegada = rs.getDate("fechaLlegada").toLocalDate();
                LocalDate fechaSalida = rs.getDate("fechaSalida").toLocalDate();
                Integer numHabitaciones = rs.getInt("numeroHabitaciones");
                String tipoHabitacion = rs.getString("tipoHabitacion");
                Integer fumador = rs.getInt("fumador");
                String regimen = rs.getString("regimenAlojamiento");
                System.out.println(regimen);
                String dniCliente = rs.getString("dniCliente");

                this.reserva = new ReservaVO(id, fechaLlegada, fechaSalida, numHabitaciones, tipoHabitacion, fumador, regimen, dniCliente);
                this.reserva.setId(id);
                this.reservas.add(this.reserva);
            }
            this.conexion.desconectarBD(conn);
            return this.reservas;
        } catch (SQLException e) {
            throw new ExcepcionHotel("Error al obtener las reservas");
        }
    }


    public void addReserva(ReservaVO m) throws ExcepcionHotel{
        String sql= "INSERT INTO Reservas (fechaLlegada, fechaSalida, numeroHabitaciones, tipoHabitacion, fumador, regimenAlojamiento, dniCliente) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try(Connection conn = this.conexion.conectarBD()){
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setDate(1, java.sql.Date.valueOf(m.getFechaLlegada()));
            ps.setDate(2, java.sql.Date.valueOf(m.getFechaSalida()));
            ps.setInt(3, m.getNumHabitaciones());
            ps.setString(4, m.getTipoHabitacion());
            ps.setInt(5, m.getFumador());
            if (reserva.getRegimen() == null || reserva.getRegimen().isEmpty()) {
                throw new ExcepcionHotel("El régimen de alojamiento no puede ser nulo o vacío.");
            }
            ps.setString(6, m.getRegimen());
            ps.setString(7, m.getDniCliente());

            ps.executeUpdate();
            ps.close();

            this.conexion.desconectarBD(conn);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new ExcepcionHotel("Error al agregar el reserva");
        }




        /*try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Reservas (fechaLlegada, fechaSalida, numeroHabitaciones, tipoHabitacion, fumador, regimenAlojamiento, dniCliente) VALUES ('" + m.getFechaLlegada() + "','" + m.getFechaSalida() + "','"  + m.getNumHabitaciones() + "','" + m.getTipoHabitacion() + "','" + m.getFumador() + "','" + m.getRegimen() + "','" + m.getDniCliente() + "');";
            this.stmt.executeUpdate(this.sentencia);
            System.out.println(m.toString());
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }*/
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
            String sql = String.format("UPDATE Reservas SET fechaLlegada = '%s', fechaSalida = '%s', numeroHabitaciones = '%s', tipoHabitacion = '%s', fumador = '%s', regimenAlojamiento = '%s', dniCliente = '%s' WHERE id = %d", reservaVO.getFechaLlegada(), reservaVO.getFechaSalida(), reservaVO.getNumHabitaciones(), reservaVO.getTipoHabitacion(), reservaVO.getFumador(), reservaVO.getRegimen(), reservaVO.getDniCliente(), lastId());
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
