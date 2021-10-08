package com.github.sharifulineugene.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Objects;

@Entity
@Table(name = "card")
@Getter
@Setter
@NoArgsConstructor
public final class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && Objects.equals(cardNumber, card.cardNumber) && Objects.equals(expDate, card.expDate) && Objects.equals(account, card.account) && status == card.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, cardNumber, expDate, account, status);
    }

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
    @JsonIgnore
    private Account account;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Card(int id, String cardNumber, String expDate, Account account, Status status) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.account = account;
        this.status = status;
    }

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
