package com.example.hotel.modelo.repository.impl;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.repository.ClienteRepository;
import com.example.hotel.modelo.ExcepcionHotel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ClienteRepositoryImpl implements ClienteRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<ClienteVO> clientes;
    private ClienteVO clienteVO;

    public ClienteRepositoryImpl() {
    }

    public ArrayList<ClienteVO> ObtenerListaCliente() throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            if (conn == null) {
                throw new ExcepcionHotel("Conexión a la base de datos fallida");
            }
            this.clientes = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM Clientes";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                String dni = rs.getString("dni");
                String nombre = rs.getString("nombre");
                String apellidos = rs.getString("apellidos");
                String direccion = rs.getString("direccion");
                String ciudad = rs.getString("ciudad");
                String provincia = rs.getString("provincia");
                this.clienteVO = new ClienteVO(dni, nombre, apellidos, direccion, ciudad, provincia);
                this.clienteVO.setDni(dni);
                this.clientes.add(this.clienteVO);
            }
            this.conexion.desconectarBD(conn);
            return this.clientes;
        } catch (SQLException var6) {
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void addCliente(ClienteVO m) throws ExcepcionHotel{
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO Clientes (dni, nombre, apellidos, direccion, ciudad, provincia) VALUES ('" + m.getDni() + "','" + m.getFirstName() + "','"  + m.getLastName() + "','" + m.getStreet() + "','" + m.getCity() + "','" + m.getProvincia() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la operación");
        }
    }

    public void deleteCliente(String dni) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM Clientes WHERE dni = %s", lastDni());
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la eliminación");
        }
    }

    public void editCliente(ClienteVO clienteVO) throws ExcepcionHotel {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE Clientes SET dni = '%s', nombre = '%s', apellidos = '%s', direccion = '%s', ciudad = '%s', provincia = '%s' WHERE dni = %s", clienteVO.getDni(), clienteVO.getFirstName(), clienteVO.getLastName(), clienteVO.getStreet(), clienteVO.getCity(), clienteVO.getProvincia(),lastDni());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
            throw new ExcepcionHotel("No se ha podido realizar la edición");
        }
    }

    public String lastDni() throws ExcepcionHotel {
        String lastClienteDni = null;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT dni FROM Clientes ORDER BY dni DESC LIMIT 1"); registro.next(); lastClienteDni = registro.getString("dni")) {
            }

            return lastClienteDni;
        } catch (SQLException var5) {
            throw new ExcepcionHotel("No se ha podido realizar la busqueda del DNI");
        }
    }
}
