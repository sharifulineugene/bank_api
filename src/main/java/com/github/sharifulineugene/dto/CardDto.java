package com.github.sharifulineugene.dto;

import com.github.sharifulineugene.entity.Card;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDto {
    private int id;
    private String cardNumber;
    private String expDate;
    private Card.Status status;
}
