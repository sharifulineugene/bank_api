package com.github.sharifulineugene.dto.mappers;

import com.github.sharifulineugene.dto.PersonDto;
import com.github.sharifulineugene.entity.Person;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class PersonMapper {
    private ModelMapper mapper;

    @Autowired
    public PersonMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Person toEntity(PersonDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto,Person.class);
    }

    public PersonDto toDto(Person entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, PersonDto.class);
    }
}
