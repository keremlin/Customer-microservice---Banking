package com.tosan.customer.Exceptions;

public class NinNotFoundException extends RuntimeException {
    public NinNotFoundException(String message){
        super(message);
    }
    public NinNotFoundException(){
        super();
    }
}
