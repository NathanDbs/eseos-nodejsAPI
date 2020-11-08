package com.eseos.tempoback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when a password does not respect security rules
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StrongPasswordException extends Exception {

    /**
     * Constructor
     * @param message the error message
     */
    public StrongPasswordException(String message) {
        super(message);
    }

}
