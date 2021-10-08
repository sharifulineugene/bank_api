package com.github.sharifulineugene.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class PersonDto {
    private int id;
    private String name;
    private String surname;
    private String date_of_birth;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonDto personDto = (PersonDto) o;
        return id == personDto.id && Objects.equals(name, personDto.name) && Objects.equals(surname, personDto.surname) && Objects.equals(date_of_birth, personDto.date_of_birth);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, date_of_birth);
    }
}


