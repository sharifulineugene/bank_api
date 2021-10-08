package com.github.sharifulineugene.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name="person")
@Getter
@Setter
@NoArgsConstructor
public class Person {
    public Person(int id, String name, String surname, String date_of_birth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.date_of_birth = date_of_birth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name) && Objects.equals(surname, person.surname) && Objects.equals(date_of_birth, person.date_of_birth) && Objects.equals(accounts, person.accounts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, date_of_birth, accounts);
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Pattern(regexp = "^(0[1-9]|1[0-9]|3[0-1])\\.(0[1-9]|1[0-2])\\.[0-9]{4}$")
    @Column(name="date_of_birth")
    private String date_of_birth;

    @OneToMany(mappedBy = "person",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Account> accounts;

    @Override
    public String toString() {
        return name +
                " " + surname + " " + date_of_birth ;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
