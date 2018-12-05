package com.anddigital.challenge.Exception;

/**
 * Defines an exception for missing resources (such as Customers and PhoneNumbers)
 */
public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}

