package com.eseos.tempoback.service;

import com.eseos.tempoback.repository.UserRepository;
import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * User service
 */
@Service
public class UserService {
    

    private final UserRepository userRepository;

    /**
     * Constructor
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Find one user by his id
     * @param id the id of the user
     * @return an optional result with the user if the id exists
     * @throws UsernameNotFoundException the exception if the user does not exists
     */
    public Optional<User> findUserById(String email) throws UsernameNotFoundException {
        return userRepository.findUserByEmail(email);
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
    public boolean userLoginExists(String email) {
        return this.userRepository.findUserByEmail(email).isPresent();
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
     * Update the user
     * @param user the user to update
     */
    public void updateUser(User user, boolean isAdmin) throws EntityNotFoundException {
        if(user.getEmail() != null){
            Optional<User> optionalUser = this.userRepository.findUserByEmail(user.getEmail());
            if (optionalUser.isPresent()) {
                user.setPassword(optionalUser.get().getPassword());
                user.setFirstName(optionalUser.get().getFirstName());
                user.setLastName(optionalUser.get().getLastName());
                user.setPicture(optionalUser.get().getPicture());
                user.setEmail(optionalUser.get().getEmail());
                if(isAdmin){
                    user.setGrade(optionalUser.get().getGrade());
                }
                this.userRepository.save(user);
            }
        }else{
            throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);
        }

    }

}
