package com.example.tutorials.Tutorials.service;

import com.example.tutorials.Tutorials.model.Tutorials;
import com.example.tutorials.Tutorials.model.TutorialsDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface TutorialsService {
    List<TutorialsDto> getAllTutorials();
    Optional<TutorialsDto> getTutorialById(String id);
    List<TutorialsDto> findByTitleContaining(String title);
    List<TutorialsDto> findByPublished();
    TutorialsDto save(TutorialsDto tutorial);
    TutorialsDto updateTutorial(TutorialsDto tutorial, String id);
    ResponseEntity deleteTutorial(String id);
    ResponseEntity deleteAllTutorials();

    // Nuevo método
    List<TutorialsDto> getTutorialsByDni(String dni);
}
