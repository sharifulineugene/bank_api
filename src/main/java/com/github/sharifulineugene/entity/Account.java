package com.github.sharifulineugene.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="account")
@Getter
@Setter
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="number")
    @Pattern(regexp="[0-9]{20}")
    private String number;
    @ManyToOne(cascade = {CascadeType.PERSIST
            , CascadeType.MERGE
            , CascadeType.DETACH
            , CascadeType.REFRESH})
    @JoinColumn(name="person_id")
    private Person person;
    @Column(name="balance")
    private Long balance;
    @OneToMany(fetch=FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "account")
    private List<Card> cards;

    public void addCardToAccount(Card card) {
        if(cards == null)
            cards = new ArrayList<>();
        cards.add(card);
        card.setAccount(this);
    }

}
