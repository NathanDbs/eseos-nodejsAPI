package com.eseos.tempoback.service;

import com.eseos.tempoback.repository.PasswordResetTokenRepository;
import com.eseos.tempoback.repository.UserRepository;
import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.model.PasswordResetToken;
import com.eseos.tempoback.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Optional;

/**
 * User service
 */
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    /**
     * Constructor
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordResetTokenRepository passwordResetTokenRepository) {
        this.userRepository = userRepository;
        this.passwordResetTokenRepository = passwordResetTokenRepository;
    }

    /**
     * Find one user by his id
     * @param id the id of the user
     * @return an optional result with the user if the id exists
     * @throws UsernameNotFoundException the exception if the user does not exists
     */
    public Optional<User> findUserById(int id) throws UsernameNotFoundException {
        return userRepository.findUserById(id);
    }

        /**
     * Find one user by his email
     * @param email the email of the user
     * @return an optional result with the user if the email exists
     * @throws UsernameNotFoundException the exception if the user does not exists
     */
    public Optional<User> findUserByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
    }

    /**
     * Check if a login is already use by one user
     * @param login the login to test
     * @return TRUE if the login already exists, else FALSE
     */
    public boolean userLoginExists(String login) {
        return this.userRepository.findUserById(Integer.parseInt(login)).isPresent();
    }

    /**
     * Save a user
     * @param user the user to save
     * @return the user saved
     */
    public User saveUser(User user) {
        return this.userRepository.save(user);
    }

    /**
     * Find all users
     * @return the users
     */
    public List<User> findAllUsers() {
        return this.userRepository.findAll();
    }

    /**
     * Fin one user with id
     * @param id the identifier of the user
     * @return an optional result with the user if the id exists
     */
    public Optional<User> findUserById(Integer id) {
        return this.userRepository.findById(id);
    }

    /**
     * The user to delete
     * @param user the user to delete
     */
    public void deleteUser(User user) {
        this.userRepository.delete(user);
    }

    /**
     * Check if a user can be deleted
     * @param user the user to check
     * @return TRUE if the user can be delete, else FALSE
     */
    public boolean canDeleteUser(User user) {
        return true;
    }

    /**
     * Create a password reset token for one user
     * @param user the user
     * @param token the token
     * @param resetPasswordExpirationTime the expiration time of the token
     */
    public void createPasswordResetTokenForUser(User user, String token, int resetPasswordExpirationTime) {
        PasswordResetToken passwordResetToken = new PasswordResetToken();
        passwordResetToken.setId(1);
        passwordResetToken.setToken(token);
        passwordResetToken.setUser(user);
        passwordResetToken.setExpiryDate(OffsetDateTime.now().plusMinutes(resetPasswordExpirationTime));

        this.passwordResetTokenRepository.save(passwordResetToken);
    }

    /**
     * Validate a password reset token
     * @param token the token to validate
     * @return the password reset token or null
     */
    public PasswordResetToken validatePasswordResetToken(String token) {
        final PasswordResetToken passwordResetToken = passwordResetTokenRepository.findPasswordResetTokenByToken(token).orElse(null);

        if (passwordResetToken == null) {
            return null;
        }
        if (passwordResetToken.getExpiryDate().isBefore(OffsetDateTime.now())) {
            return null;
        }
        return passwordResetToken;
    }

    /**
     * Delete a password reset token for one user
     * @param user the user
     */
    @Transactional
    public void deletePasswordResetTokenForUser(User user) {
        this.passwordResetTokenRepository.deletePasswordResetTokensByUser(user);
    }

        /**
     * Update the user
     * @param user the user to delete
     */
    public void updateUser(User user) throws EntityNotFoundException {
        if(user.getId() != null){
            Optional<User> optionalUser = this.userRepository.findById(user.getId());
            if (optionalUser.isPresent()) {
                user.setPassword(optionalUser.get().getPassword());
                this.userRepository.save(user);
            }
        }else{
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        }

    }

}
