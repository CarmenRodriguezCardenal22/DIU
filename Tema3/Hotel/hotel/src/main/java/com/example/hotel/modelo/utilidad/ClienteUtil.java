package com.example.hotel.modelo.utilidad;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.vista.Cliente;

import java.util.ArrayList;

public class ClienteUtil {
    public static ArrayList<Cliente> pasarClienteLista(ArrayList<ClienteVO> clientesVO) {
        ArrayList<Cliente> clientes = new ArrayList<>();
        for(ClienteVO clienteVO: clientesVO) {
            clientes.add(new Cliente(clienteVO.getDni(),clienteVO.getFirstName(),clienteVO.getLastName(),clienteVO.getStreet(), clienteVO.getCity(), clienteVO.getProvincia()));
        }
        return clientes;
    }
    public static ClienteVO pasarClienteVO(Cliente cliente) {
        ClienteVO clientesVO = new ClienteVO();
        clientesVO.setDni(cliente.getDni());
        clientesVO.setFirstName(cliente.getFirstName());
        clientesVO.setLastName(cliente.getLastName());
        clientesVO.setStreet(cliente.getStreet());
        clientesVO.setCity(cliente.getCity());
        clientesVO.setProvincia(cliente.getProvincia());
        return clientesVO;
    }
}
