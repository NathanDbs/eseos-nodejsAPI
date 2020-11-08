package com.eseos.tempoback.errorhandling;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * The tempo error template
 */
@Getter
@Setter
@ToString
public class TempoError {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date timestamp;
    private HttpStatus status;
    private String message;
    private String exception;

    /**
     * Constructor of TempoError
     * @param status the error status
     * @param message the error message
     * @param exception the type of the error (the error class)
     */
    public TempoError(HttpStatus status, String message, String exception) {
        this.timestamp = new Date();
        this.status = status;
        this.message = message;
        this.exception = exception;
    }

}
