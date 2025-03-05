package com.example.tutorials.Tutorials.model;

import lombok.*;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Builder
public class Tutorials {
    private String id;
    private String title;
    private String description;
    private Boolean published;
    private String foto;
    private String dni; // Nuevo campo para asociar tutoriales con una persona
}



