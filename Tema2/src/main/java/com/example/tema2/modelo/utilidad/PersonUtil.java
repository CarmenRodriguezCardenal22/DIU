package com.example.tema2.modelo.utilidad;

import com.example.tema2.modelo.PersonVO;
import com.example.tema2.vista.Person;

import java.util.ArrayList;

public class PersonUtil {
    public static ArrayList<PersonVO> pasarPersonVO(ArrayList<Person> personas) {
        ArrayList<PersonVO> personasVO = new ArrayList<>();
        for(Person persona: personas) {
            personasVO.add(new PersonVO(persona.getFirstName(),persona.getLastName(),persona.getStreet(),persona.getPostalCode(), persona.getCity(), persona.getBirthday()));
        }
        return personasVO;
    }

    public static ArrayList<Person> pasarPerson(ArrayList<PersonVO> personasVO) {
        ArrayList<Person> personas = new ArrayList<>();
        for(PersonVO personaVO: personasVO) {
            personas.add(new Person(personaVO.getFirstName(),personaVO.getLastName(),personaVO.getStreet(),personaVO.getPostalCode(), personaVO.getCity(), personaVO.getBirthday()));
        }
        return personas;
    }
}
