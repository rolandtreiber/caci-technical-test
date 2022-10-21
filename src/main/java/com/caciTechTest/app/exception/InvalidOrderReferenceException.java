package com.caciTechTest.app.exception;

public class InvalidOrderReferenceException extends RuntimeException {

    public InvalidOrderReferenceException() {
        super("Invalid order reference");
    }
}
