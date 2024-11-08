package com.example.hotel.modelo;

import java.time.LocalDate;

public class ReservaVO {
    Integer id, numHabitaciones;
    String tipoHabitacion, regimen, dniCliente;
    LocalDate fechaLlegada, fechaSalida;
    Boolean fumador;

    public ReservaVO() {this(0,null,null,0,null,false, null,null);}

    public ReservaVO(Integer id,LocalDate fechaLlegada, LocalDate fechaSalida, Integer numHabitaciones, String tipoHabitacion, Boolean fumador, String regimen, String dniCliente) {
        this.id = id;
        this.fechaLlegada = fechaLlegada;
        this.fechaSalida = fechaSalida;
        this.numHabitaciones = numHabitaciones;
        this.tipoHabitacion = tipoHabitacion;
        this.fumador = fumador;
        this.regimen = regimen;
        this.dniCliente = dniCliente;
    }

    public int getId() {return this.id;}
    public void setId(int id) {this.id = id;}

    public LocalDate getFechaLlegada() {return this.fechaLlegada;}
    public void setFechaLlegada(LocalDate fechaLlegada) {this.fechaLlegada = fechaLlegada;}

    public LocalDate getFechaSalida() {return this.fechaSalida;}
    public void setFechaSalida(LocalDate fechaSalida) {this.fechaSalida = fechaSalida;}

    public Integer getNumHabitaciones() {return this.numHabitaciones;}
    public void setNumHabitaciones(Integer numHabitaciones) {this.numHabitaciones = numHabitaciones;}

    public String getTipoHabitacion() {return this.tipoHabitacion;}
    public void setTipoHabitacion(String tipoHabitacion) {this.tipoHabitacion = tipoHabitacion;}

    public Boolean getFumador() {return this.fumador;}
    public void setFumador(Boolean fumador) {this.fumador = fumador;}

    public String getRegimen() {return this.regimen;}
    public void setRegimen(String regimen) {this.regimen = regimen;}

    public String getDniCliente() {return this.dniCliente;}
    public void setDniCliente(String dniCliente) {this.dniCliente = dniCliente;}

    public String toString() {
        return "ReservaVO { id = " + this.id + ", fecha de llegada = " + this.fechaLlegada +
                ", fecha de salida = " + this.fechaSalida + ", número de habitaciones = " + this.numHabitaciones +
                ", tipo de habitación = " + this.tipoHabitacion + ", fumador = " + this.fumador +
                ", régimen de alojamiento = " + this.regimen + ", dni del cliente = " + this.dniCliente + "}\n";
    }
}
