package com.example.hotel.vista;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Cliente {

    private final StringProperty dni;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty street;
    private final StringProperty city;
    private final StringProperty provincia;

    public Cliente() {
        this(null, null, null, null, null, null);
    }

    public Cliente(String dni,String firstName, String lastName, String street, String city, String provincia) {
        this.dni = new SimpleStringProperty(dni);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.street = new SimpleStringProperty(street);
        this.city = new SimpleStringProperty(city);
        this.provincia = new SimpleStringProperty(provincia);
    }

    public String getDni() {return dni.get();}

    public void setDni(String dni) {this.dni.set(dni);}

    public String getFirstName() {return firstName.get();}

    public void setFirstName(String firstName) {this.firstName.set(firstName);}

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }

    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }

    public String getProvincia() {
        return provincia.get();
    }

    public void setProvincia(String provincia) {
        this.provincia.set(provincia);
    }

    public StringProperty provinciaProperty() {
        return provincia;
    }
}
