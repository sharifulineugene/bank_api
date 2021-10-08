package com.github.sharifulineugene.dto;

import com.github.sharifulineugene.entity.Card;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class CardDto {
    private int id;
    private String cardNumber;
    private String expDate;
    private Card.Status status;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CardDto cardDto = (CardDto) o;
        return id == cardDto.id && Objects.equals(cardNumber, cardDto.cardNumber) && Objects.equals(expDate, cardDto.expDate) && status == cardDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, expDate, status);
    }
}
