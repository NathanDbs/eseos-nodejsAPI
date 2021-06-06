package com.eseos.tempoback.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.eseos.tempoback.dto.UserDTO;
import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.exceptions.RelatedDataException;
import com.eseos.tempoback.exceptions.StrongPasswordException;
import com.eseos.tempoback.exceptions.UniqueFieldException;
import com.eseos.tempoback.model.User;
import com.eseos.tempoback.service.UserService;
import com.eseos.tempoback.validator.StrongPasswordValidator;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

/**
 * Controller for users
 */
@RestController
@Slf4j
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
