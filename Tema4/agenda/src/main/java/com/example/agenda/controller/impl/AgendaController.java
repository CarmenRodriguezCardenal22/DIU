package com.example.agenda.controller.impl;

import com.example.agenda.controller.AgendaAPI;
import com.example.agenda.model.PersonDto;
import com.example.agenda.repository.AgendaRepository;
import com.example.agenda.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1")
public class AgendaController implements AgendaAPI {
    @Autowired
    private AgendaService agendaService;
    @Autowired
    private AgendaRepository agendaRepository;
    @Override
    @GetMapping("/Agenda")
    public List<PersonDto> getAllAgenda(){return agendaService.getAllAgenda();}

    @Override
    @GetMapping("/Agenda/{dni}")
    public Optional<PersonDto> getAgendaByDni(@PathVariable String dni) {
        return agendaService.getAgendaByDni(dni);
    }

    @Override
    @GetMapping("/Agenda/name/{name}")
    public List<PersonDto> findByNameContaining(@PathVariable String name) {
        return agendaService.findByNameContaining(name);
    }

    @Override
    @PostMapping("/Agenda")
    public PersonDto save(@RequestBody PersonDto personDto) {
        return agendaService.save(personDto);
    }

    @Override
    @PutMapping("/Agenda/{dni}")
    public PersonDto updatePerson(@RequestBody PersonDto person, @PathVariable String dni) {
        return agendaService.updatePerson(person);
    }

    @Override
    @DeleteMapping("/Agenda/{dni}")
    public ResponseEntity deletePerson(@PathVariable String dni) {
        return agendaService.deletePerson(dni);
    }

    @Override
    @DeleteMapping("/Agenda")
    public ResponseEntity deleteAllAgenda() {
        return agendaService.deleteAllAgenda();
    }
}
