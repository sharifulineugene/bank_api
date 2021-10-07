package com.github.sharifulineugene.excception_handling.card;

public class NoSuchCardException extends RuntimeException{
    public NoSuchCardException() {
    }

    public NoSuchCardException(String message) {
        super(message);
    }
}
