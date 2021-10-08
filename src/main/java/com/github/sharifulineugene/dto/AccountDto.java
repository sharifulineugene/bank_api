package com.github.sharifulineugene.dto;

import com.github.sharifulineugene.entity.Person;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
public class AccountDto {
    private int id;
    private String number;
    private PersonDto person;
    private long balance;
    private List<CardDto> cards;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountDto that = (AccountDto) o;
        return id == that.id && balance == that.balance && Objects.equals(number, that.number) && Objects.equals(person, that.person) && Objects.equals(cards, that.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, person, balance, cards);
    }
}
