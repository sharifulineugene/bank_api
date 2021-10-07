package com.github.sharifulineugene.excception_handling.account;

import lombok.NoArgsConstructor;

public class NoSuchAccountException extends RuntimeException{
    public NoSuchAccountException() {
    }

    public NoSuchAccountException(String message) {
        super(message);
    }
}
