package com.eseos.tempoback.dto;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.eseos.tempoback.model.enums.GradeEnum;

import javax.validation.constraints.*;

/**
 * User DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserDTO {

    @NotEmpty(message = ErrorUtils.USER_PASSWORD_EMPTY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 10, max = 100)
    private String _password;

    @NotEmpty(message = ErrorUtils.USER_FIRST_NAME_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_FIRST_NAME_SIZE)
    private String firstName;

    @NotEmpty(message = ErrorUtils.USER_LAST_NAME_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_LAST_NAME_SIZE)
    private String lastName;

    @NotEmpty(message = ErrorUtils.USER_EMAIL_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_EMAIL_SIZE)
    @Email(message = ErrorUtils.USER_EMAIL_FORMAT)
    private String mail;

    private GradeEnum grade;

    private String picture;

}
