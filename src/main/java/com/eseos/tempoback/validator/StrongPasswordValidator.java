package com.eseos.tempoback.validator;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.exceptions.StrongPasswordException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class StrongPasswordValidator {

    /**
     * Check if a password is enough secured
     * @param password the password to check
     * @throws StrongPasswordException the exception for not secured password
     */
    public void isValid(String password) throws StrongPasswordException {
        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[$&+,:;=?@#|'<>.^*()%!-])(?=\\S+$).{10,}$", password)) {
            throw new StrongPasswordException(ErrorUtils.USER_PASSWORD_SECURITY);
        }
    }

}
