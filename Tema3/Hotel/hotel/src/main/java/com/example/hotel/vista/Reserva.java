package com.example.hotel.vista;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Reserva {
    private final IntegerProperty id;
    private final ObjectProperty<LocalDate> fechaLlegada;
    private final ObjectProperty<LocalDate> fechaSalida;
    private final IntegerProperty numHabitaciones;
    private final StringProperty tipoHabitacion;
    private final IntegerProperty fumador;
    private final StringProperty regimen;
    private final StringProperty dniCliente;

    public Reserva() {
        this(0, null, null, 0, null, 0, null, null);
    }

    public Reserva(Integer id,LocalDate fechaLlegada, LocalDate fechaSalida, Integer numHabitaciones, String tipoHabitacion, Integer fumador, String regimen, String dniCliente) {
        this.id = new SimpleIntegerProperty(id);
        this.fechaLlegada = new SimpleObjectProperty<LocalDate>(fechaLlegada);
        this.fechaSalida = new SimpleObjectProperty<LocalDate>(fechaSalida);
        this.numHabitaciones = new SimpleIntegerProperty(numHabitaciones);
        this.tipoHabitacion = new SimpleStringProperty(tipoHabitacion);
        this.fumador = new SimpleIntegerProperty(fumador);
        this.regimen = new SimpleStringProperty(regimen);
        this.dniCliente = new SimpleStringProperty(dniCliente);
    }

    public Integer getId() {return id.get();}

    public void setId(Integer id) {this.id.set(id);}

    public IntegerProperty idProperty() {
        return id;
    }

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

    public Integer getFumador() {
        return fumador.get();
    }

    public void setFumador(Integer fumador) {
        this.fumador.set(fumador);
    }

    public IntegerProperty fumadorProperty() {
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
    private List<String> regimenSeleccionado = new ArrayList<>();

    public void setRegimenSeleccionado(List<String> regimenSeleccionado) {
        this.regimenSeleccionado = regimenSeleccionado;
    }
    public List<String> getRegimenOpciones() {
        return Arrays.asList("Alojamiento y Desayuno", "Media Pensión", "Pensión Completa");
    }

    public List<String> getRegimenSeleccionado() {
        return regimenSeleccionado;
    }

}

