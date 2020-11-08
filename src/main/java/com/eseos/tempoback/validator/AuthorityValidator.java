package com.eseos.tempoback.validator;

import com.eseos.tempoback.model.enums.AuthorityEnum;
import com.eseos.tempoback.validator.constraint.AuthorityConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Authority validator
 */
public class AuthorityValidator implements ConstraintValidator<AuthorityConstraint, String> {

    /**
     * Validate an authority
     * @param authority the authority to validate
     * @param constraintValidatorContext the constraint validator context
     * @return TRUE if the authority is valid, else FALSE
     */
    @Override
    public boolean isValid(String authority, ConstraintValidatorContext constraintValidatorContext) {
        if (authority == null) {
            return false;
        }

        try {
            AuthorityEnum.valueOf(authority.toUpperCase());
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }

}
