package com.example.tema2.modelo;

import com.example.tema2.modelo.repository.PersonRepository;
import com.example.tema2.modelo.utilidad.PersonUtil;

import java.util.ArrayList;

public class AgendaModelo {
    PersonRepository personRepository;
    PersonUtil personUtil=new PersonUtil();
    public void setPersonRepository(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    public ArrayList<PersonVO> obtenerPersonas() throws ExceptionPerson {
        ArrayList<PersonVO> listaPersonas=personRepository.ObtenerListaPersonas();
        return listaPersonas;
    }
    //public void mostrarPersonas()
}
