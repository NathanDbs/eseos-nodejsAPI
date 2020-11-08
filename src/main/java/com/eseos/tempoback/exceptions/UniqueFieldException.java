package com.eseos.tempoback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when a field should be unique
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UniqueFieldException extends Exception {

    /**
     * Constructor
     * @param message the error message
     */
    public UniqueFieldException(String message) {
        super(message);
    }

}
