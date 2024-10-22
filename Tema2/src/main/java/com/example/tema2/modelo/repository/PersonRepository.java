package com.example.tema2.modelo.repository;

import com.example.tema2.modelo.ExceptionPerson;
import com.example.tema2.modelo.PersonVO;

import java.util.ArrayList;

public interface PersonRepository {
    ArrayList<PersonVO> ObtenerListaMonedas() throws ExceptionPerson;

    void addPerson(PersonVO var1) throws ExceptionPerson;

    void deletePerson(Integer var1) throws ExceptionPerson;

    void editPerson(PersonVO var1) throws ExceptionPerson;

    int lastId() throws ExceptionPerson;
}
