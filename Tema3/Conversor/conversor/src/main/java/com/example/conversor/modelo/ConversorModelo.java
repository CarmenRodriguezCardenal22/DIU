package com.example.conversor.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import Modelo.repository.impl.MonedaRepositoryImpl;
import com.example.conversor.controller.HelloController;

import java.util.ArrayList;

public class ConversorModelo {
    private MonedaRepository monedaRepository;
    public void setMonedaRepository(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }

    public float obtenerMulti() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas=monedaRepository.ObtenerListaMonedas();
        MonedaVO dolarVO=listaMonedas.get(0);
        return dolarVO.getMultiplicador();
    }

    public double conversion(double euros) throws ExcepcionMoneda {
        return euros * obtenerMulti();
    }
}
