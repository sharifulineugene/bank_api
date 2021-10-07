package com.github.sharifulineugene.excception_handling.person;

public class NoSuchPersonException extends RuntimeException{
    public NoSuchPersonException() {
    }

    public NoSuchPersonException(String message) {
        super(message);
    }
}
