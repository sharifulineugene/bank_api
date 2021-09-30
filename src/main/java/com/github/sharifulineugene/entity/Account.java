package com.github.sharifulineugene.entity;

public class Account {
    private final int id;
    private final String number;
    private final Person owner;

    public Account(int id, String number, Person owner) {
        this.id = id;
        this.number = number;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public Person getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", number=" + number +
                ", owner=" + owner +
                '}';
    }
}
