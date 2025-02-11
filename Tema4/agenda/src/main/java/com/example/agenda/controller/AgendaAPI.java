package com.example.agenda.controller;

import com.example.agenda.model.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AgendaAPI {
    List<PersonDto> getAllAgenda();
    Optional<PersonDto> getAgendaByDni(String dni);
    List<PersonDto> findByNameContaining(String name);
    PersonDto save(PersonDto person);
    PersonDto updatePerson(PersonDto person, String dni);
    ResponseEntity deletePerson(String dni);
    ResponseEntity deleteAllAgenda();
}
