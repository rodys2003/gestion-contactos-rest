package com.rersdev.gestioncontactos.configuration.exception.contact;

public class ContactNotFoundException extends RuntimeException{

    public ContactNotFoundException(String message) {
        super(message);
    }
}
