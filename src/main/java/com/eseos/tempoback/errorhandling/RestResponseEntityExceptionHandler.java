package com.eseos.tempoback.errorhandling;

import com.eseos.tempoback.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * The controller advice to handle and send custom exceptions
 */
@ControllerAdvice
@Slf4j
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handle profile restart exceptions
     * @param ex the runtime exception
     * @param request the web request
     * @return an internal error
     */
    @ExceptionHandler(value = {ProfileRestartException.class})
    protected ResponseEntity<Object> handleProfileRestartException(RuntimeException ex, WebRequest request) {
        log.error(request.getContextPath(), ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    /**
     * Handle app not running exceptions
     * @param ex the runtime exception
     * @param request the web request
     * @return an internal error
     */
    @ExceptionHandler(value = {AppIsNotRunningException.class})
    protected ResponseEntity<Object> AppIsNotRunningException(RuntimeException ex, WebRequest request) {
        log.error(request.getContextPath(), ex);
        return handleExceptionInternal(ex, ex.getMessage(), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    /**
     * Handle not valid method argument exceptions
     * @param ex the not valid method argument exception
     * @param headers the headers
     * @param status the http status of the error
     * @param request the web request
     * @return the response entity with the error
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.error(request.getContextPath(), ex);
        String message = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        TempoError tempoError = new TempoError(HttpStatus.BAD_REQUEST, message, ex.getClass().getSimpleName());

        return new ResponseEntity<>(tempoError, headers, status);
    }

    /**
     * Handle custom bad requests exceptions
     * @param ex the exception
     * @param request the web request
     * @return the response entity with the error
     */
    @ExceptionHandler(value = {
            StrongPasswordException.class,
            UniqueFieldException.class,
    })
    protected ResponseEntity<Object> handleBadRequestsException(Exception ex, WebRequest request) {
        log.error(request.getContextPath(), ex);
        TempoError tempoError = new TempoError(HttpStatus.BAD_REQUEST, ex.getMessage(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(tempoError, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle custom not found exceptions
     * @param ex the exception
     * @param request the web request
     * @return the response entity with the error
     */
    @ExceptionHandler(value = {
            EntityNotFoundException.class
    })
    protected ResponseEntity<Object> handleNotFoundException(Exception ex, WebRequest request) {
        log.error(request.getContextPath(), ex);
        TempoError tempoError = new TempoError(HttpStatus.NOT_FOUND, ex.getMessage(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(tempoError, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handle custom forbidden exceptions
     * @param ex the exception
     * @param request the web request
     * @return the response entity with the error
     */
    @ExceptionHandler(value = {
            RelatedDataException.class,
            BadCredentialsException.class
    })
    protected ResponseEntity<Object> handleForbiddenException(Exception ex, WebRequest request) {
        log.error(request.getContextPath(), ex);
        TempoError tempoError = new TempoError(HttpStatus.FORBIDDEN, ex.getMessage(), ex.getClass().getSimpleName());
        return new ResponseEntity<>(tempoError, new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    /**
     * Handle all others exceptions
     * @param ex the exception
     * @param request the web request
     * @return an internal error
     */
    @ExceptionHandler({
            Exception.class,
            MailException.class
    })
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        log.error(request.getContextPath(), ex);
        TempoError tempoError = new TempoError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getClass().getSimpleName());

        return new ResponseEntity<>(tempoError, new HttpHeaders(), tempoError.getStatus());
    }

}
