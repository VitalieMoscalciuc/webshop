package com.vmoscalciuc.providedServices.exception;

public class ProvidedServiceNotFoundException extends RuntimeException{
    public ProvidedServiceNotFoundException(String message) {
            super(message);
        }
    }
