package com.example.hotel.vista;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Reserva {
    private final IntegerProperty id;
    private final ObjectProperty<LocalDate> fechaLlegada;
    private final ObjectProperty<LocalDate> fechaSalida;
    private final IntegerProperty numHabitaciones;
    private final StringProperty tipoHabitacion;
    private final BooleanProperty fumador;
    private final StringProperty regimen;
    private final StringProperty dniCliente;

    public Reserva() {
        this(0, null, null, 0, null, false, null, null);
    }

    public Reserva(Integer id,LocalDate fechaLlegada, LocalDate fechaSalida, Integer numHabitaciones, String tipoHabitacion, Boolean fumador, String regimen, String dniCliente) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaLlegada = new SimpleObjectProperty<LocalDate>(fechaLlegada);
        this.fechaSalida = new SimpleObjectProperty<LocalDate>(fechaSalida);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.fumador = new SimpleBooleanProperty(fumador);
        this.regimen = new SimpleStringProperty(regimen);
        this.dniCliente = new SimpleStringProperty(dniCliente);
    }
    public int getId() {return id.get();}

    public void setId(int id) {this.id.set(id);}

    public LocalDate getFechaLlegada() {return fechaLlegada.get();}

    public void setFechaLlegada(LocalDate fechaLlegada) {this.fechaLlegada.set(fechaLlegada);}

    public ObjectProperty<LocalDate> fechaLlegadaProperty() {
        return fechaLlegada;
    }

    public LocalDate getFechaSalida() {
        return fechaSalida.get();
    }

    public void setFechaSalida(LocalDate fechaSalida) {
        this.fechaSalida.set(fechaSalida);
    }

    public ObjectProperty<LocalDate> fechaSalidaProperty() {
        return fechaSalida;
    }

    public Integer getNumHabitaciones() {
        return numHabitaciones.get();
    }

    public void setNumHabitaciones(Integer numHabitaciones) {
        this.numHabitaciones.set(numHabitaciones);
    }

    public IntegerProperty numHabitacionesProperty() {
        return numHabitaciones;
    }

    public String getTipoHabitacion() {
        return tipoHabitacion.get();
    }

    public void setTipoHabitacion(String tipoHabitacion) {
        this.tipoHabitacion.set(tipoHabitacion);
    }

    public StringProperty tipoHabitacionProperty() {
        return tipoHabitacion;
    }

    public Boolean getFumador() {
        return fumador.get();
    }

    public void setFumador(Boolean fumador) {
        this.fumador.set(fumador);
    }

    public BooleanProperty fumadorProperty() {
        return fumador;
    }

    public String getRegimen() {
        return regimen.get();
    }

    public void setRegimen(String regimen) {
        this.regimen.set(regimen);
    }

    public StringProperty regimenProperty() {
        return regimen;
    }

    public String getDniCliente() {
        return dniCliente.get();
    }
    public void setDniCliente(String dniCliente) {
        this.dniCliente.set(dniCliente);
    }
    public StringProperty dniClienteProperty() {
        return dniCliente;
    }
}

