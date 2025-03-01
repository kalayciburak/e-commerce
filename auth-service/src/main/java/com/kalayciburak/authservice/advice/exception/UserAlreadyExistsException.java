package com.kalayciburak.authservice.advice.exception;

import jakarta.persistence.EntityExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends EntityExistsException {
    public UserAlreadyExistsException() {
        super("Kullanıcı zaten mevcut.");
    }

    public UserAlreadyExistsException(String username) {
        super("Kullanıcı zaten mevcut: " + username);
    }
}
