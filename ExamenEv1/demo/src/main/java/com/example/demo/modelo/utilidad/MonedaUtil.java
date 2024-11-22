package com.example.demo.modelo.utilidad;

import Modelo.MonedaVO;
import com.example.demo.vista.Moneda;

import java.util.ArrayList;

public class MonedaUtil {
    public static ArrayList<Moneda> pasarMonedaLista(ArrayList<MonedaVO> monedasVO) {
        ArrayList<Moneda> monedas = new ArrayList<>();
        for(MonedaVO monedaVO: monedasVO) {
            monedas.add(new Moneda(monedaVO.getCodigo(),monedaVO.getNombre(),monedaVO.getMultiplicador()));
        }
        return monedas;
    }
    /*public static MonedaVO pasarMonedaVO(Moneda moneda) {
        MonedaVO monedasVO = new MonedaVO();
        monedasVO.setCodigo(moneda.getCodigo());
        monedasVO.setNombre(moneda.getNombre());
        monedasVO.setMultiplicador(moneda.getMultiplicador());
        return monedasVO;
    }*/
}
