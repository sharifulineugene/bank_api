package com.github.sharifulineugene.dto.mappers;

import com.github.sharifulineugene.dto.CardDto;
import com.github.sharifulineugene.entity.Card;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

public class CardMapperTest {

    @Test
    public void shouldConvertDto() {
        Card card = new Card(1,"0000000000000000","11/23",null,Card.Status.NEW);
        CardDto dtoExpected = new CardDto();
        dtoExpected.setId(1);
        dtoExpected.setCardNumber("0000000000000000");
        dtoExpected.setExpDate("11/23");
        dtoExpected.setStatus(Card.Status.NEW);
        CardDto dtoActual = new CardMapper(new ModelMapper()).toDto(card);
        Assertions.assertEquals(dtoExpected,dtoActual);
    }

    @Test
    public void shouldConvertEntity() {
        Card entityExpected = new Card(1,"0000000000000000","11/23",null,Card.Status.NEW);
        CardDto dto = new CardDto();
        dto.setId(1);
        dto.setCardNumber("0000000000000000");
        dto.setExpDate("11/23");
        dto.setStatus(Card.Status.NEW);
        Card entityActual = new CardMapper(new ModelMapper()).toEntity(dto);
        Assertions.assertEquals(entityExpected,entityActual);
    }

}
