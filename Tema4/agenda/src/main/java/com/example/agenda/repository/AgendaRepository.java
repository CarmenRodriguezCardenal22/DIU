package com.example.agenda.repository;

import com.example.agenda.model.PersonVO;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AgendaRepository extends MongoRepository<PersonVO, String> {
    List<PersonVO>  findAll();
    Optional<PersonVO> getAgendaByDni(String dni);
    List<PersonVO> findByNameContaining(String name);
}
