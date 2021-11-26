package com.bouta.billingservice;

public class CustomerNotFoundException extends RuntimeException {
    public CustomerNotFoundException(String messages) {
        super(messages);
    }
}
