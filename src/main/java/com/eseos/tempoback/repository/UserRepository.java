package com.eseos.tempoback.repository;

import com.eseos.tempoback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find one user using the id
     * @param id the id
     * @return the user or an empty optional result
     */
    Optional<User> findUserById(Integer id);

       /**
     * Find one user using the email
     * @param email the email
     * @return the user or an empty optional result
     */
    Optional<User> findUserByEmail(String mail);

}
