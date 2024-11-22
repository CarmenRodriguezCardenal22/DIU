package com.example.demo.modelo;

import Modelo.ExcepcionMoneda;
import Modelo.MonedaVO;
import Modelo.repository.MonedaRepository;
import com.example.demo.modelo.utilidad.MonedaUtil;
import com.example.demo.vista.Moneda;

import java.util.ArrayList;

public class MonedaModelo {
    static MonedaRepository monedaRepository;
    public void setMonedaRepository(MonedaRepository monedaRepository) {
        this.monedaRepository = monedaRepository;
    }
    public static ArrayList<MonedaVO> obtenerMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas=monedaRepository.ObtenerListaMonedas();
        return listaMonedas;
    }
    public ArrayList<Moneda> mostrarMonedas() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaVO=obtenerMonedas();
        ArrayList<Moneda> lista= MonedaUtil.pasarMonedaLista(listaVO);
        return lista;
    }
    /*public float obtenerMulti() throws ExcepcionMoneda {
        ArrayList<MonedaVO> listaMonedas=monedaRepository.ObtenerListaMonedas();
        MonedaVO dolarVO=listaMonedas.get(0);
        return dolarVO.getMultiplicador();
    }
    public double conversion(double euros) throws ExcepcionMoneda {
        return euros * obtenerMulti();
    }*/
    public void deleteMoneda(Integer id) throws ExcepcionMoneda {
        monedaRepository.deleteMoneda(id);
    }
}
