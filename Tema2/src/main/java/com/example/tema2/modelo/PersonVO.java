package com.example.tema2.modelo;

import java.time.LocalDate;

public class PersonVO {
    int cod;
    String firstName, lastName, street, postalCode, city;
    LocalDate birthday;

    public PersonVO(String firstName, String lastName, String street, String postalCode, String city, LocalDate birthday) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.street = street;
        this.postalCode = postalCode;
        this.city = city;
        this.birthday = birthday;
    }

    public int getCod() {return this.cod;}
    public void setCod(int cod) {this.cod = cod;}

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

    public String getPostalCode() {return this.postalCode;}
    public void setPostalCode(String postalCode) {this.postalCode = postalCode;}

    public String getCity() {return this.city;}
    public void setCity(String city) {this.city = city;}

    public LocalDate getBirthday() {return this.birthday;}
    public void setBirthday(LocalDate birthday) {this.birthday = birthday;}

    public String toString() {
        return "MonedaVO { nombre = " + this.firstName + ", apellidos = " + this.lastName +
                ", dirección = " + this.street + ", código postal = " + this.postalCode +
                ", ciudad = " + this.city + ", birthday = " + this.birthday + "}";
    }
}
