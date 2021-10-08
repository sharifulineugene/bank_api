package com.github.sharifulineugene.dto.mappers;

import com.github.sharifulineugene.dto.PersonDto;
import com.github.sharifulineugene.entity.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class PersonMapperTest {

    @Test
    public void shouldConvertDto() {
        Person person = new Person(1,"name","surname","01.01.1991");
        PersonDto dtoExpected = new PersonDto();
        dtoExpected.setId(1);
        dtoExpected.setName("name");
        dtoExpected.setSurname("surname");
        dtoExpected.setDate_of_birth("01.01.1991");
        PersonDto dtoActual = new PersonMapper(new ModelMapper()).toDto(person);
        Assertions.assertEquals(dtoExpected,dtoActual);
    }

    @Test
    public void shouldConvertEntity() {
        Person entityExpected = new Person(1,"name","surname","01.01.1991");
        PersonDto dto = new PersonDto();
        dto.setId(1);
        dto.setName("name");
        dto.setSurname("surname");
        dto.setDate_of_birth("01.01.1991");
        Person entityActual = new PersonMapper(new ModelMapper()).toEntity(dto);
        Assertions.assertEquals(entityExpected,entityActual);
    }

}
