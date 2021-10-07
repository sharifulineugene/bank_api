package com.github.sharifulineugene.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PersonDto {
    private int id;
    private String name;
    private String surname;
    private String date_of_birth;
    private List<AccountDto> accounts;
}


