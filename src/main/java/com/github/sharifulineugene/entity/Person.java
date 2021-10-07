package com.github.sharifulineugene.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.List;

@Entity
@Table(name="person")
@Getter
@Setter
public class Person {
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
