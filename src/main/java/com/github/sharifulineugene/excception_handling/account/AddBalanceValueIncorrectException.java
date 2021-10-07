package com.github.sharifulineugene.excception_handling.account;

public class AddBalanceValueIncorrectException extends RuntimeException{
    public AddBalanceValueIncorrectException() {
    }

    public AddBalanceValueIncorrectException(String message) {
        super(message);
    }
}
