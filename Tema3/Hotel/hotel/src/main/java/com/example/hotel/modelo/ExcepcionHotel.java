package com.example.hotel.modelo;

public class ExcepcionHotel extends Exception {
    private String mensaje;

    public ExcepcionHotel() {
    }

    public ExcepcionHotel(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
