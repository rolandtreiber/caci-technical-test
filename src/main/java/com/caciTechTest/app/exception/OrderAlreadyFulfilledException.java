package com.caciTechTest.app.exception;

public class OrderAlreadyFulfilledException extends RuntimeException {

    public OrderAlreadyFulfilledException() {
        super("Order already fulfilled");
    }
}
