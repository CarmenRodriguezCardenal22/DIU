package com.example.tutorials.Tutorials.service.impl;

import com.example.tutorials.Tutorials.model.Tutorials;
import com.example.tutorials.Tutorials.model.TutorialsDto;
import com.example.tutorials.Tutorials.repository.TutorialsRepository;
import com.example.tutorials.Tutorials.service.TutorialsService;
import com.example.tutorials.Tutorials.util.TutorialsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.VariableOperators;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TutorialsServiceImpl implements TutorialsService {
    @Autowired
    private TutorialsRepository tutorialsRepository;

    @Override
    public List<TutorialsDto> getAllTutorials(){
        List<Tutorials> tutorialsList = tutorialsRepository.findAll();
        return tutorialsList.stream()
                .map(TutorialsMapper::tutorialsMapperEntityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<TutorialsDto> getTutorialById(String id) {
        Optional<Tutorials> tutorialOptional = tutorialsRepository.findById(id);

        return tutorialOptional.map(TutorialsMapper::tutorialsMapperEntityToDto);
    }

    @Override
    public List<TutorialsDto> findByTitleContaining(String title) {
        List<Tutorials> tutorialOptional = tutorialsRepository.findByTitleContaining(title);

        return TutorialsMapper.tutorialsListMapperEntityToDto(tutorialOptional);
    }

    @Override
    public List<TutorialsDto> findByPublished() {
        List<Tutorials> publishedTutorials = tutorialsRepository.findByPublished(true);

        return TutorialsMapper.tutorialsListMapperEntityToDto(publishedTutorials);
    }

    @Override
    public TutorialsDto save(TutorialsDto tutorialDto) {
        Tutorials tutorials = TutorialsMapper.tutorialsMapperDtoToEntity(tutorialDto);
        Tutorials savedTutorialEntity = tutorialsRepository.save(tutorials);
        return TutorialsMapper.tutorialsMapperEntityToDto(savedTutorialEntity);
    }

    @Override
    public TutorialsDto updateTutorial(TutorialsDto tutorial, String id) {
        // Verificar si el tutorial existe
        Optional<Tutorials> existingTutorial = tutorialsRepository.findById(id);
        if (existingTutorial.isPresent()) {
            Tutorials updatedTutorial = existingTutorial.get();
            updatedTutorial.setTitle(tutorial.getTitle());
            updatedTutorial.setDescription(tutorial.getDescription());
            updatedTutorial.setPublished(tutorial.getPublished());
            updatedTutorial.setFoto(tutorial.getFoto());
            updatedTutorial.setDni(tutorial.getDni()); // Si agregaste el campo DNI
            tutorialsRepository.save(updatedTutorial);
            return TutorialsMapper.tutorialsMapperEntityToDto(updatedTutorial);
        } else {
            // Aquí puedes manejar el caso de que el tutorial no exista
            throw new RuntimeException("Tutorial no encontrado");
        }
    }

    @Override
    public ResponseEntity deleteTutorial(String id) {
        try {
            Optional<Tutorials> existingTutorialOptional = tutorialsRepository.findById(id);
            if (existingTutorialOptional.isPresent()) {
                tutorialsRepository.deleteById(id);
                return ResponseEntity.ok("Tutorial eliminado exitosamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tutorial no encontrado con ID: " + id);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el tutorial");
        }
    }

    @Override
    public ResponseEntity deleteAllTutorials() {
        tutorialsRepository.deleteAll();
        ResponseEntity.ok("Tutorial eliminado exitosamente");
        return ResponseEntity.ok().build();
    }

    @Override
    public List<TutorialsDto> getTutorialsByDni(String dni) {
        List<Tutorials> tutorials = tutorialsRepository.findByDniAndPublishedTrue(dni);
        return TutorialsMapper.tutorialsListMapperEntityToDto(tutorials);
    }


}
