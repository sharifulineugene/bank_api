package com.github.sharifulineugene.entity;


public final class Card {
    private final int id;
    private final String cardNumber;
    private final String expDate;
    private final Account account;

    public Card(int id,String cardNumber, String expDate, Account account) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expDate = expDate;
        this.account = account;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getExpDate() {
        return expDate;
    }

    public Account getAccount() {
        return account;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardNumber='" + cardNumber + '\'' +
                ", expDate='" + expDate + '\'' +
                ", account=" + account +
                '}';
    }
}
