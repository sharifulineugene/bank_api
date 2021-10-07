package com.github.sharifulineugene.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "card")
@Getter
@Setter
public final class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "card_number")
    private String cardNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])\\/?([0-9]{4}|[0-9]{2})$")
    @Column(name = "exp_date")
    private String expDate;

    @ManyToOne(cascade={CascadeType.MERGE
            ,CascadeType.PERSIST
            ,CascadeType.DETACH
            ,CascadeType.REFRESH})
    @JoinColumn(name="account_id")
    private Account account;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate='" + expDate + '\'' +
                ", account=" + account +
                ", status=" + status +
                '}';
    }

    public enum Status {
        ACTIVE, NEW, BLOCKED;
    }
}
