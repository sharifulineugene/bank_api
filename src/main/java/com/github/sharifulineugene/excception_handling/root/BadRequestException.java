package com.github.sharifulineugene.excception_handling.root;

import lombok.NoArgsConstructor;

public class BadRequestException extends RuntimeException {
    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }
}
