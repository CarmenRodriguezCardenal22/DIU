package com.example.tema2.modelo;

public class ExceptionPerson extends Exception {
    private String mensaje;

    public ExceptionPerson() {
    }

    public ExceptionPerson(String ms) {
        this.mensaje = ms;
    }

    public String imprimirMensaje() {
        return this.mensaje;
    }
}
