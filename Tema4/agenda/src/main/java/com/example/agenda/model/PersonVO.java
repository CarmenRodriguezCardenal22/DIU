package com.example.agenda.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder
public class PersonVO {
    @Id
    private String dni;
    private String name;
    private String lastname;
    private String street;
    private String postalCode;
    private String city;
    private LocalDate birthday;
}
