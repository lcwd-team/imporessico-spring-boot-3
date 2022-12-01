package com.lcwd.store.excetions;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
        super("Bad Request !!");
    }

    public BadRequestException(String message) {
        super(message);
    }

}
