package com.eseos.tempoback.controller;

import com.eseos.tempoback.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller for users
 */
@RestController
public class TestController {

    @Autowired
    private UserService userService;

    /**
     * Get the API status
     * 
     * @return the current status
     */
    @GetMapping(value = "/healthCheck", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus healthCheck() {
        return HttpStatus.OK;
    }

    /**
     * Get the Database Status
     * 
     * @return the Database status
     */
    @GetMapping(value="/database", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public HttpStatus database() {
        if(this.userService.findAllUsers().size() != 0){
             return HttpStatus.OK;
        }else{
            return HttpStatus.FAILED_DEPENDENCY;
        }
    }

}
