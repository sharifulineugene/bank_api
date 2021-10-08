package com.github.sharifulineugene.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="account")
@Getter
@Setter
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Objects.equals(number, account.number) && Objects.equals(person, account.person) && Objects.equals(balance, account.balance) && Objects.equals(cards, account.cards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number, person, balance, cards);
    }

    @Column(name="number")
    @Pattern(regexp="[0-9]{20}")
    private String number;

    public Account(int id, String number, Person person, Long balance) {
        this.id = id;
        this.number = number;
        this.person = person;
        this.balance = balance;
    }

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
