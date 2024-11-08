package com.example.hotel.modelo;

import java.time.LocalDate;

public class ClienteVO {
    String dni, firstName, lastName, street, city, provincia;

    public ClienteVO() {this(null,null,null,null,null,null);}

    public ClienteVO(String dni,String firstName, String lastName, String street, String city, String provincia) {
        this.dni = dni;
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.city = city;
        this.provincia = provincia;
    }

    public String getDni() {return this.dni;}
    public void setDni(String dni) {this.dni = dni;}

    public String getFirstName() {
        return this.firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {return this.lastName;}
    public void setLastName(String lastName) {this.lastName = lastName;}

    public String getStreet() {return this.street;}
    public void setStreet(String street) {this.street = street;}

    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    public String getProvincia() {return this.provincia;}
    public void setProvincia(String provincia) {this.provincia = provincia;}

    public String toString() {
        return "ClienteVO { dni = " + this.dni + ", nombre = " + this.firstName +
                ", apellidos = " + this.lastName + ", dirección = " + this.street +
                ", ciudad = " + this.city + ", provincia = " + this.provincia + "}\n";
    }
}
