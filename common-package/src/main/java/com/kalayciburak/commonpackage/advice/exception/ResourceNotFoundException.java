package com.kalayciburak.commonpackage.advice.exception;

/**
 * Herhangi bir kaynak bulunamadığında fırlatılacak exception sınıfı.
 */
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

