package com.example.hotel.modelo;

import com.example.hotel.modelo.repository.ClienteRepository;
import com.example.hotel.modelo.repository.ReservaRepository;

import java.util.ArrayList;

public class HotelModelo {
    static ClienteRepository clienteRepository;
    static ReservaRepository reservaRepository;
    public void setClienteRepository(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    public void setReservaRepository(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }
    public static ArrayList<ClienteVO> obtenerClientes() throws ExcepcionHotel {
        ArrayList<ClienteVO> listaClientes=clienteRepository.ObtenerListaCliente();
        return listaClientes;
    }
    public static ArrayList<ReservaVO> obtenerReservas() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaReservas=reservaRepository.ObtenerListaReserva();
        return listaReservas;
    }
}
