package com.example.hotel.modelo.utilidad;

import com.example.hotel.modelo.ClienteVO;
import com.example.hotel.modelo.ReservaVO;
import com.example.hotel.vista.Cliente;
import com.example.hotel.vista.Reserva;

import java.util.ArrayList;

public class ReservaUtil {
    public static ArrayList<Reserva> pasarReservaLista(ArrayList<ReservaVO> reservasVO) {
        ArrayList<Reserva> reservas = new ArrayList<>();
        for(ReservaVO reservaVO: reservasVO) {
            reservas.add(new Reserva(reservaVO.getId(),reservaVO.getFechaLlegada(),reservaVO.getFechaSalida(),reservaVO.getNumHabitaciones(), reservaVO.getTipoHabitacion(), reservaVO.getFumador(), reservaVO.getRegimen(), reservaVO.getDniCliente()));
        }
        return reservas;
    }
    public static ReservaVO pasarReservaVO(Reserva reserva) {
        ReservaVO reservaVO = new ReservaVO();
        reservaVO.setId(reserva.getId());
        reservaVO.setFechaLlegada(reserva.getFechaLlegada());
        reservaVO.setFechaSalida(reserva.getFechaSalida());
        reservaVO.setNumHabitaciones(reserva.getNumHabitaciones());
        reservaVO.setTipoHabitacion(reserva.getTipoHabitacion());
        reservaVO.setFumador(reserva.getFumador());
        reservaVO.setRegimen(reserva.getRegimenInsertado());
        reservaVO.setDniCliente(reserva.getDniCliente());
        return reservaVO;
    }
}
