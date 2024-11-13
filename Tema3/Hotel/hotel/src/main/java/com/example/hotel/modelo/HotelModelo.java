package com.example.hotel.modelo;

import com.example.hotel.modelo.repository.ClienteRepository;
import com.example.hotel.modelo.repository.ReservaRepository;
import com.example.hotel.modelo.utilidad.ClienteUtil;
import com.example.hotel.vista.Cliente;

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
    public void addCliente(Cliente cliente) throws ExcepcionHotel {
        ClienteVO personVO= ClienteUtil.pasarClienteVO(cliente);
        clienteRepository.addCliente(personVO);
    }
    public void deleteCliente(String dni) throws ExcepcionHotel {
        clienteRepository.deleteCliente(dni);
    }
    public void editCliente(Cliente person) throws ExcepcionHotel {
        ClienteVO personVO=ClienteUtil.pasarClienteVO(person);
        clienteRepository.editCliente(personVO);
    }
}
