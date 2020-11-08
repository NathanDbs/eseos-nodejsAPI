package com.eseos.tempoback.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception when an entity is linked to others data
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class RelatedDataException extends Exception {

    /**
     * Constructor
     * @param message the error message
     */
    public RelatedDataException(String message) {
        super(message);
    }

}
