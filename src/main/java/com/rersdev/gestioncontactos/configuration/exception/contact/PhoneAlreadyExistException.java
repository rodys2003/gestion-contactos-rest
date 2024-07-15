package com.rersdev.gestioncontactos.configuration.exception.contact;

public class PhoneAlreadyExistException extends RuntimeException{

    public PhoneAlreadyExistException(String message) {
        super(message);
    }
}
