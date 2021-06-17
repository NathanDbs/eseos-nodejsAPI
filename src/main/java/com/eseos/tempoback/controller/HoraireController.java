package com.eseos.tempoback.controller;

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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Controller for users
 */
@RestController
@RequestMapping(value = "/horaire")
public class HoraireController {

    @Value( "1800" )
    private int resetPasswordExpirationTime;

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private StrongPasswordValidator strongPasswordValidator;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Get the current user
     * @return the current user
     */
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getCurrentUser(Principal principal) {
        Optional<User> potentialUser = this.userService.findUserByEmail(principal.getName());
        if (potentialUser.isEmpty()) {
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        }

        return modelMapper.map(potentialUser.get(), UserDTO.class);
    }

    /**
     * Get all users
     * @return a list of users
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return this.userService.findAllUsers()
                .stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Add a new user
     * @param userDTO the user to add
     * @return the user created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO) throws UniqueFieldException, StrongPasswordException {
        strongPasswordValidator.isValid(userDTO.getPassword());

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        User newUser = this.userService.saveUser(modelMapper.map(userDTO, User.class));
        return ResponseEntity.created(ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUser.getId())
                .toUri())
                .body(modelMapper.map(newUser, UserDTO.class));
    }

    /**
     * Delete one user if it's possible
     * @param id the identifier of the user
     * @throws RelatedDataException an exception if the user is related with others datas
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable("id") String id) throws RelatedDataException {
        Optional<User> potentialUser = this.userService.findUserById(Integer.parseInt(id));
        if (potentialUser.isEmpty()) {
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        }

        final User user = potentialUser.get();
        if (!this.userService.canDeleteUser(user)) {
            throw new RelatedDataException(ErrorUtils.DELETE_USER_FORBIDDEN);
        }

        this.userService.deleteUser(user);
    }

}
