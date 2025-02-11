package com.example.agenda.service.impl;

import com.example.agenda.model.PersonVO;
import com.example.agenda.model.PersonDto;
import com.example.agenda.repository.AgendaRepository;
import com.example.agenda.service.AgendaService;
import com.example.agenda.util.AgendaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AgendaServiceImpl implements AgendaService {
    @Autowired
    private AgendaRepository agendaRepository;

    @Override
    public List<PersonDto> getAllAgenda(){
        List<PersonVO> personList = agendaRepository.findAll();
        return personList.stream()
                .map(AgendaMapper::AgendaMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<PersonDto> getAgendaByDni(String dni) {
        Optional<PersonVO> personOptional = agendaRepository.findById(dni);

        return personOptional.map(AgendaMapper::AgendaMapperEntityToDto);
    }

    @Override
    public List<PersonDto> findByNameContaining(String name) {
        List<PersonVO> personOptional = agendaRepository.findByNameContaining(name);

        return AgendaMapper.AgendaListMapperEntityToDto(personOptional);
    }

    @Override
    public PersonDto save(PersonDto personDto) {
        PersonVO Person = AgendaMapper.AgendaMapperDtoToEntity(personDto);
        PersonVO savedPersonEntity = agendaRepository.save(Person);
        return AgendaMapper.AgendaMapperEntityToDto(savedPersonEntity);
    }

    @Override
    public PersonDto updatePerson(PersonDto person) {
        Optional<PersonVO> existingAgendaOptional = agendaRepository.findById(person.getDni());

        if (existingAgendaOptional.isPresent()) {
            PersonVO existingPerson = existingAgendaOptional.get();
            existingPerson.setName(person.getName());
            existingPerson.setLastname(person.getLastname());
            existingPerson.setStreet(person.getStreet());
            existingPerson.setPostalCode(person.getPostalCode());
            existingPerson.setCity(person.getCity());
            existingPerson.setBirthday(person.getBirthday());

            PersonVO updatedPerson = agendaRepository.save(existingPerson);

            return AgendaMapper.AgendaMapperEntityToDto(updatedPerson);
        } else {
            return null;
        }
    }

    @Override
    public ResponseEntity deletePerson(String dni) {
        try {
            Optional<PersonVO> existingPersonOptional = agendaRepository.findById(dni);
            if (existingPersonOptional.isPresent()) {
                agendaRepository.deleteById(dni);
                return ResponseEntity.ok("Persona eliminada exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Persona no encontrada con DNI: " + dni);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la persona");
        }
    }

    @Override
    public ResponseEntity deleteAllAgenda() {
        agendaRepository.deleteAll();
        ResponseEntity.ok("Agenda eliminada exitosamente");
        return ResponseEntity.ok().build();
    }

}
