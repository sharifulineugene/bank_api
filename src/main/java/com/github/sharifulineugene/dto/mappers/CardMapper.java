package com.github.sharifulineugene.dto.mappers;

import com.github.sharifulineugene.dto.CardDto;
import com.github.sharifulineugene.entity.Card;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class CardMapper {
    private ModelMapper mapper;

    @Autowired
    public CardMapper(ModelMapper mapper) {
        this.mapper = mapper;
    }

    public Card toEntity(CardDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto,Card.class);
    }

    public CardDto toDto(Card entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, CardDto.class);
    }
}
