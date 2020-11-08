package com.eseos.tempoback.repository;

import com.eseos.tempoback.model.PasswordResetToken;
import com.eseos.tempoback.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Password reset token repository
 */
@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

    /**
     * Find a password reset token with the token
     * @param token the token to search
     * @return an optional password reset token
     */
    Optional<PasswordResetToken> findPasswordResetTokenByToken(String token);

    /**
     * Delete all reset password tokens by user
     * @param user the user
     */
    void deletePasswordResetTokensByUser(User user);

}
