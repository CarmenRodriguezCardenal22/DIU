package com.example.tema2.modelo.repository.impl;

import com.example.tema2.modelo.ExceptionPerson;
import com.example.tema2.modelo.PersonVO;
import com.example.tema2.modelo.repository.PersonRepository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class PersonRepositoryImpl implements PersonRepository {
    private final ConexionJDBC conexion = new ConexionJDBC();
    private Statement stmt;
    private String sentencia;
    private ArrayList<PersonVO> personas;
    private PersonVO persona;

    public PersonRepositoryImpl() {
    }

    public ArrayList<PersonVO> ObtenerListaPersonas() throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            if (conn == null) {
                throw new ExceptionPerson("Conexión a la base de datos fallida");
            }
            this.personas = new ArrayList();
            this.stmt = conn.createStatement();
            this.sentencia = "SELECT * FROM personas";
            ResultSet rs = this.stmt.executeQuery(this.sentencia);

            while (rs.next()) {
                Integer codigo = rs.getInt("Código");
                String nombre = rs.getString("Nombre");
                String apellidos = rs.getString("Apellidos");
                String direccion = rs.getString("Dirección");
                String codigoPostal = rs.getString("Código Postal");
                String ciudad = rs.getString("Ciudad");
                LocalDate fechaNacimiento = rs.getDate("Fecha de Nacimiento").toLocalDate();
                PersonVO persona = new PersonVO(codigo, nombre, apellidos, direccion, codigoPostal, ciudad, fechaNacimiento);
                persona.setCod(codigo);
                this.personas.add(persona);
            }
            this.conexion.desconectarBD(conn);
            return this.personas;
        } catch (SQLException var6) {
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }
    }

    public void addPerson(PersonVO m) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            this.sentencia = "INSERT INTO personas (nombre, apellidos, direccion, codigoPostal, ciudad, fechaNacimiento) VALUES ('" + m.getFirstName() + "','" + m.getLastName() + "','"  + m.getStreet() + "','" + m.getPostalCode() + "','" + m.getCity() + "','" + m.getBirthday() + "');";
            this.stmt.executeUpdate(this.sentencia);
            this.stmt.close();
            this.conexion.desconectarBD(conn);
        } catch (SQLException var3) {
            System.out.println(var3.getMessage());
            throw new ExceptionPerson("No se ha podido realizar la operación");
        }
    }

    public void deletePerson(Integer idPerson) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            Statement comando = conn.createStatement();
            String sql = String.format("DELETE FROM personas WHERE Código = %d", idPerson);
            comando.executeUpdate(sql);
            this.conexion.desconectarBD(conn);
        } catch (SQLException var5) {
            System.out.println(var5.getMessage());
            throw new ExceptionPerson("No se ha podido realizar la eliminación");
        }
    }

    public void editPerson(PersonVO personVO) throws ExceptionPerson {
        try {
            Connection conn = this.conexion.conectarBD();
            this.stmt = conn.createStatement();
            String sql = String.format("UPDATE personas SET Código= '%s', Nombre = '%s', Apellidos = '%s', Dirección = '%s', Código Postal = '%s', Ciudad = '%s', Fecha de Nacimiento = '%s' WHERE Código = %s", personVO.getFirstName(), personVO.getLastName(), personVO.getStreet(),personVO.getPostalCode(), personVO.getCity(), personVO.getBirthday());
            this.stmt.executeUpdate(sql);
        } catch (Exception var4) {
            System.out.println(var4.getMessage());
            throw new ExceptionPerson("No se ha podido realizar la edición");
        }
    }

    public int lastId() throws ExceptionPerson {
        int lastPersonId = 0;

        try {
            Connection conn = this.conexion.conectarBD();
            Statement comando = conn.createStatement();

            for(ResultSet registro = comando.executeQuery("SELECT codigo FROM personas ORDER BY codigo DESC LIMIT 1"); registro.next(); lastPersonId = registro.getInt("codigo")) {
            }

            return lastPersonId;
        } catch (SQLException var5) {
            throw new ExceptionPerson("No se ha podido realizar la busqueda del ID");
        }
    }
}
