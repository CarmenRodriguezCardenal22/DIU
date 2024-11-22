package com.example.tema2.modelo;

import com.example.tema2.modelo.repository.PersonRepository;
import com.example.tema2.modelo.utilidad.PersonUtil;
import com.example.tema2.vista.Person;

import java.util.ArrayList;

public class AgendaModelo {
    static PersonRepository personRepository;
    public void setPersonRepository(PersonRepository personRepository) {

        this.personRepository = personRepository;
    }
    public static ArrayList<PersonVO> obtenerPersonas() throws ExceptionPerson {
        ArrayList<PersonVO> listaPersonas=personRepository.ObtenerListaPersonas();
        return listaPersonas;
    }
    public ArrayList<Person> mostrarPersonas() throws ExceptionPerson {
        ArrayList<PersonVO> listaVO=obtenerPersonas();
        ArrayList<Person> lista= PersonUtil.pasarPersonLista(listaVO);
        return lista;
    }
    public void addPersonas(Person person) throws ExceptionPerson {
        PersonVO personVO=PersonUtil.pasarPersonVO(person);
        personRepository.addPerson(personVO);
    }
    public void deletePersonas(Integer id) throws ExceptionPerson {
        personRepository.deletePerson(id);
    }
    public void editPersonas(Person person) throws ExceptionPerson {
        PersonVO personVO=PersonUtil.pasarPersonVO(person);
        personRepository.editPerson(personVO);
    }
}
