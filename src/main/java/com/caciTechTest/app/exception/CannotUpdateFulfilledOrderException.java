package com.caciTechTest.app.exception;

public class CannotUpdateFulfilledOrderException extends RuntimeException {

    public CannotUpdateFulfilledOrderException() {
        super("Cannot update a fulfilled order");
    }
}
