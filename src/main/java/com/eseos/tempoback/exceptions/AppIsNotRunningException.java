package com.eseos.tempoback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when the app is not running
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AppIsNotRunningException extends RuntimeException{

    /**
     * Constructor
     * @param message the error message
     */
    public AppIsNotRunningException(String message){
        super(message);
    }

}
