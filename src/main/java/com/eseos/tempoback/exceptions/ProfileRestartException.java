package com.eseos.tempoback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when the profile restart does not work
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProfileRestartException extends RuntimeException {

    /**
     * Constructor
     * @param message the error message
     */
    public ProfileRestartException(String message){
        super(message);
    }

}
