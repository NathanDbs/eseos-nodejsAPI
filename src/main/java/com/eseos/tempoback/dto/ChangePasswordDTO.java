package com.eseos.tempoback.dto;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Change password DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ChangePasswordDTO {

    @NotEmpty(message = ErrorUtils.USER_PASSWORD_EMPTY)
    @Size(min = 10, max = 100)
    private String newPassword;

}
