package com.example.hotel.modelo;

import com.example.hotel.modelo.repository.ClienteRepository;
import com.example.hotel.modelo.repository.ReservaRepository;
import com.example.hotel.modelo.utilidad.ClienteUtil;
import com.example.hotel.modelo.utilidad.ReservaUtil;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;

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
    public ArrayList<Cliente> mostrarClientes() throws ExcepcionHotel {
        ArrayList<ClienteVO> listaVO=obtenerClientes();
        ArrayList<Cliente> lista= ClienteUtil.pasarClienteLista(listaVO);
        return lista;
    }
    public static ArrayList<ReservaVO> obtenerReservas(String dni) throws ExcepcionHotel {
        if (dni == null || dni.trim().isEmpty()) {
            throw new ExcepcionHotel("El DNI no puede estar vacío.");
        }
        return reservaRepository.ObtenerListaReserva(dni);
    }
    /*public ArrayList<Reserva> mostrarReserva() throws ExcepcionHotel {
        ArrayList<ReservaVO> listaVO=obtenerReservas();
        ArrayList<Reserva> lista= ReservaUtil.pasarReservaLista(listaVO);
        return lista;
    }*/
    public void addCliente(Cliente cliente) throws ExcepcionHotel {
        ClienteVO clienteVO= ClienteUtil.pasarClienteVO(cliente);
        clienteRepository.addCliente(clienteVO);
    }
    public void deleteCliente(String dni) throws ExcepcionHotel {
        clienteRepository.deleteCliente(dni);
    }
    public void editCliente(Cliente cliente) throws ExcepcionHotel {
        ClienteVO clienteVO=ClienteUtil.pasarClienteVO(cliente);
        clienteRepository.editCliente(clienteVO);
    }
    public void addReserva(Reserva reserva) throws ExcepcionHotel {
        ReservaVO reservaVO= ReservaUtil.pasarReservaVO(reserva);
        reservaRepository.addReserva(reservaVO);
    }
    public void deleteReserva(Integer id) throws ExcepcionHotel {
        reservaRepository.deleteReserva(id);
    }
    public void editReserva(Reserva reserva) throws ExcepcionHotel {
        ReservaVO reservaVO= ReservaUtil.pasarReservaVO(reserva);
        reservaRepository.editReserva(reservaVO);
    }
}
