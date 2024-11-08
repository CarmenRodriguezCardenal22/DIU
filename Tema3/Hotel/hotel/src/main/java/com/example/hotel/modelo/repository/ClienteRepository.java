package com.example.hotel.modelo.repository;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ExcepcionHotel;

import java.util.ArrayList;

public interface ClienteRepository {
    ArrayList<ClienteVO> ObtenerListaCliente() throws ExcepcionHotel;

    void addCliente(ClienteVO var1) throws ExcepcionHotel;

    void deleteCliente(String var1) throws ExcepcionHotel;

    void editCliente(ClienteVO var1) throws ExcepcionHotel;

    String lastDni() throws ExcepcionHotel;
}
