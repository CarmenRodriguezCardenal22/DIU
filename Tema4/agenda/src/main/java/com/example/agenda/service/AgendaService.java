package com.example.agenda.service;

import com.example.agenda.model.PersonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    List<PersonDto> getAllAgenda(); //funciona
    Optional<PersonDto> getAgendaByDni(String dni); //funciona
    List<PersonDto> findByNameContaining(String name);
    PersonDto save(PersonDto person); //funciona
    PersonDto updatePerson(PersonDto person); //funciona
    ResponseEntity deletePerson(String cod); //funciona
    ResponseEntity deleteAllAgenda(); //funciona
}
