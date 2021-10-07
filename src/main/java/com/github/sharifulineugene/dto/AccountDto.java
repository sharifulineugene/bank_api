package com.github.sharifulineugene.dto;

import com.github.sharifulineugene.entity.Person;
import lombok.*;

import java.util.List;

@Getter
@Setter
public class AccountDto {
    private int id;
    private String number;
    private Person person;
    private long balance;
    private List<CardDto> cards;


}
