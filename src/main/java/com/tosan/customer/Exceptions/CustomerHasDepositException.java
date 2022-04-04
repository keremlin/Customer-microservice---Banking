package com.tosan.customer.Exceptions;

public class CustomerHasDepositException extends RuntimeException {
    public CustomerHasDepositException(String message){
        super(message);
    }
}
