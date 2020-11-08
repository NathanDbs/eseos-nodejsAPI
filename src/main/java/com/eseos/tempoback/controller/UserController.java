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
@RequestMapping(value = "/user")
public class UserController {

    @Value("${tempo.user.reset.password.expiration.time}")
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
     * 
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
     * 
     * @return a list of users
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers() {
        return this.userService.findAllUsers().stream().map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Add a new user
     * 
     * @param userDTO the user to add
     * @return the user created
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> addUser(@Valid @RequestBody UserDTO userDTO, Principal principal)
            throws UniqueFieldException, StrongPasswordException {
        Optional<User> potentialUser = this.userService.findUserByEmail(principal.getName());
        if (potentialUser.isEmpty()) {
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        } else if (potentialUser.get().getRang().equals("5")) {

            strongPasswordValidator.isValid(userDTO.getPassword());

            userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

            User newUser = this.userService.saveUser(modelMapper.map(userDTO, User.class));

            return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                    .buildAndExpand(newUser.getId()).toUri()).body(modelMapper.map(newUser, UserDTO.class));
        } else {
            throw new EntityNotFoundException(ErrorUtils.ADD_USER_FORBIDDEN);
        }
    }

    /**
     * Update one user if it's possible
     * 
     * @param userDTO the user to update
     */
    @PostMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody UserDTO userDTO, Principal principal) {
        Optional<User> potentialUser = this.userService.findUserByEmail(principal.getName());
        if (potentialUser.isEmpty()) {
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        } else if (potentialUser.get().getRang().equals("5") || potentialUser.get().getId() == userDTO.getId()) {
            this.userService.updateUser(modelMapper.map(userDTO, User.class));
        } else {
            throw new EntityNotFoundException(ErrorUtils.ADD_USER_FORBIDDEN);
        }
    }

    /**
     * Delete one user if it's possible
     * 
     * @param id the identifier of the user
     * @throws RelatedDataException an exception if the user is related with others
     *                              datas
     */
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable("id") String id, Principal principal) throws RelatedDataException {
        Optional<User> potentialUser = this.userService.findUserByEmail(principal.getName());
        if (potentialUser.isEmpty()) {
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        } else if (potentialUser.get().getRang().equals("5") && potentialUser.get().getId() != Integer.parseInt(id)) {
            final User user = potentialUser.get();
            if (!this.userService.canDeleteUser(user)) {
                throw new RelatedDataException(ErrorUtils.DELETE_USER_FORBIDDEN);
            }
            this.userService.deleteUser(user);
        } else {
            throw new RelatedDataException(ErrorUtils.ADD_USER_FORBIDDEN);
        }
    }

    /**
     * Asking to reset a user password
     * 
     * @param request the http servlet request
     * @param login   the login of the user
     */
    /*
     * @PostMapping(value = "/resetPassword")
     * 
     * @ResponseStatus(HttpStatus.NO_CONTENT) public void
     * resetPassword(HttpServletRequest request, @RequestParam("login") String
     * login) { Optional<User> potentialUser =
     * this.userService.findUserByEmail(login); if (potentialUser.isEmpty()) { throw
     * new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND); } User user =
     * potentialUser.get(); String token = UUID.randomUUID().toString();
     * userService.createPasswordResetTokenForUser(user, token,
     * resetPasswordExpirationTime); try {
     * mailService.sendResetUserPasswordMail(request.getHeaders("referer").
     * nextElement(), token, user, resetPasswordExpirationTime); } catch
     * (MessagingException | IOException | TemplateException ex) { throw new
     * MailSendException(ErrorUtils.ENVOI_MAIL); } }
     */

    /**
     * Check if the user can reset the password
     * 
     * @param token the token
     */
    /*
     * @PostMapping(value = "/resetPassword/check")
     * 
     * @ResponseStatus(HttpStatus.NO_CONTENT) public void
     * checkResetPassword(@RequestParam("token") String token) { PasswordResetToken
     * passwordResetToken = userService.validatePasswordResetToken(token); if
     * (passwordResetToken == null) { throw new
     * BadCredentialsException(ErrorUtils.RESET_PASSWORD_INVALID_TOKEN); } }
     */

    /**
     * Save the new password of the user in reset context
     * 
     * @param resetPasswordDTO the new password with the correct token
     * @throws StrongPasswordException strong password exception
     */
    /*
     * @PostMapping(value = "/resetPassword/save", consumes =
     * MediaType.APPLICATION_JSON_VALUE)
     * 
     * @ResponseStatus(HttpStatus.NO_CONTENT) public void
     * saveResetPassword(@Valid @RequestBody ResetPasswordDTO resetPasswordDTO)
     * throws StrongPasswordException { PasswordResetToken passwordResetToken =
     * userService.validatePasswordResetToken(resetPasswordDTO.getToken()); if
     * (passwordResetToken == null) { throw new
     * BadCredentialsException(ErrorUtils.RESET_PASSWORD_INVALID_TOKEN); }
     * strongPasswordValidator.isValid(resetPasswordDTO.getNewPassword());
     * 
     * User user = passwordResetToken.getUser();
     * user.setPassword(passwordEncoder.encode(resetPasswordDTO.getNewPassword()));
     * userService.saveUser(user);
     * 
     * userService.deletePasswordResetTokenForUser(user); }
     */

    /**
     * Change the password of the current user
     * 
     * @param oldPassword
     * @param newPassword
     * @throws StrongPasswordException
     */
    /*
     * @PostMapping(value = "/changePassword", consumes =
     * MediaType.APPLICATION_JSON_VALUE)
     * 
     * @ResponseStatus(HttpStatus.NO_CONTENT) public void
     * changePassword(@RequestBody String oldPassword, @RequestBody
     * ChangePasswordDTO newPassword, Principal principal) throws
     * StrongPasswordException, WrongPasswordException { Optional<User> optionalUser
     * = this.userService.findUserByEmail(principal.getName()); if
     * (optionalUser.isEmpty()){ throw new
     * EntityNotFoundException(ErrorUtils.USER_NOT_FOUND); }else if
     * (!passwordEncoder.matches(oldPassword,optionalUser.get().getPassword())){
     * throw new WrongPasswordException(ErrorUtils.WRONG_PASSWORD); }
     * 
     * strongPasswordValidator.isValid(newPassword.getNewPassword());
     * 
     * User user = optionalUser.get();
     * user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
     * userService.saveUser(user); }
     */

}
