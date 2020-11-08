package com.eseos.tempoback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when the wrong password is given
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongPasswordException extends Exception {

    /**
     * Constructor
     * @param message the error message
     */
    public WrongPasswordException(String message) {
        super(message);
    }

}
