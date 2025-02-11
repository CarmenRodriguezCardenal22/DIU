package com.example.agenda.util;

import com.example.agenda.model.PersonVO;
import com.example.agenda.model.PersonDto;

import java.util.List;
import java.util.stream.Collectors;

public class AgendaMapper {
    public static PersonVO AgendaMapperDtoToEntity(PersonDto person){
        return PersonVO.builder()
                .dni(person.getDni())
                .name(person.getName())
                .lastname(person.getLastname())
                .street(person.getStreet())
                .postalCode(person.getPostalCode())
                .city(person.getCity())
                .birthday(person.getBirthday())
                .build();
    }

    public static PersonDto AgendaMapperEntityToDto(PersonVO person){
        return PersonDto.builder()
                .dni(person.getDni())
                .name(person.getName())
                .lastname(person.getLastname())
                .street(person.getStreet())
                .postalCode(person.getPostalCode())
                .city(person.getCity())
                .birthday(person.getBirthday())
                .build();
    }


    public static List<PersonVO> AgendaListMapperDtoToEntity(List<PersonDto> AgendaDtoList) {
        return AgendaDtoList.stream()
                .map(AgendaMapper::AgendaMapperDtoToEntity)
                .collect(Collectors.toList());
    }


    public static List<PersonDto> AgendaListMapperEntityToDto(List<PersonVO> personList) {
        return personList.stream()
                .map(AgendaMapper::AgendaMapperEntityToDto)
                .collect(Collectors.toList());
    }
}
