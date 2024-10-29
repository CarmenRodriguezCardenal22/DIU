package com.example.tema2.modelo.utilidad;

import com.example.tema2.modelo.PersonVO;
import com.example.tema2.vista.Person;

import java.util.ArrayList;

public class PersonUtil {
    public static ArrayList<PersonVO> pasarPersonVOLista(ArrayList<Person> personas) {
        ArrayList<PersonVO> personasVO = new ArrayList<>();
        for(Person persona: personas) {
            personasVO.add(new PersonVO(persona.getCod(),persona.getFirstName(),persona.getLastName(),persona.getStreet(),persona.getPostalCode(), persona.getCity(), persona.getBirthday()));
        }
        return personasVO;
    }

    public static ArrayList<Person> pasarPersonLista(ArrayList<PersonVO> personasVO) {
        ArrayList<Person> personas = new ArrayList<>();
        for(PersonVO personaVO: personasVO) {
            personas.add(new Person(personaVO.getCod(),personaVO.getFirstName(),personaVO.getLastName(),personaVO.getStreet(),personaVO.getPostalCode(), personaVO.getCity(), personaVO.getBirthday()));
        }
        return personas;
    }
    public static PersonVO pasarPersonVO(Person persona) {
        PersonVO personasVO = new PersonVO();
        personasVO.setCod(persona.getCod());
        personasVO.setFirstName(persona.getFirstName());
        personasVO.setLastName(persona.getLastName());
        personasVO.setStreet(persona.getStreet());
        personasVO.setPostalCode(persona.getPostalCode());
        personasVO.setCity(persona.getCity());
        personasVO.setBirthday(persona.getBirthday());
        return personasVO;
    }

    public static Person pasarPerson(PersonVO personaVO) {
        Person personas = new Person();
        personas.setCod(personaVO.getCod());
        personas.setFirstName(personaVO.getFirstName());
        personas.setLastName(personaVO.getLastName());
        personas.setStreet(personaVO.getStreet());
        personas.setPostalCode(personaVO.getPostalCode());
        personas.setCity(personaVO.getCity());
        personas.setBirthday(personaVO.getBirthday());
        return personas;
    }
}
