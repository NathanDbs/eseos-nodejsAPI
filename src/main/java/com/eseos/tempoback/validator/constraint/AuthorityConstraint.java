package com.eseos.tempoback.validator.constraint;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.validator.AuthorityValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Authority validator annotation
 */
@Documented
@Constraint(validatedBy = AuthorityValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AuthorityConstraint {
    String message() default ErrorUtils.AUTHORITY_NAME_INVALID;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
