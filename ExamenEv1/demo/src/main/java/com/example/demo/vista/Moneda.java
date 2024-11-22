package com.example.demo.vista;

import javafx.beans.property.*;

public class Moneda {
    private final StringProperty nombre;
    private final IntegerProperty codigo;
    private final FloatProperty multiplicador;

    public Moneda(){this(0, null, 0);}
    public Moneda(Integer codigo, String nombre, float multiplicador) {
        this.codigo = new SimpleIntegerProperty(codigo);
        this.nombre = new SimpleStringProperty(nombre);
        this.multiplicador = new SimpleFloatProperty(multiplicador);
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public StringProperty nombreProperty() {return nombre;}

    public Integer getCodigo() {
        return codigo.get();
    }

    public void setCodigo(Integer codigo) {this.codigo.set(codigo);}

    public IntegerProperty codigoProperty() {return codigo;}

    public float getMultiplicador() {
        return multiplicador.get();
    }

    public void setMultiplicador(float multiplicador) {
        this.multiplicador.set(multiplicador);
    }

    public FloatProperty multiplicadorProperty() {return multiplicador;}

    public String toString() {
        return "Moneda{codigo=" + this.codigo + ", nombre=" + this.nombre + ", multiplicador=" + this.multiplicador + '}';
    }
}
