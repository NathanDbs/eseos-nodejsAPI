package com.eseos.tempoback.dto;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Reset password DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ResetPasswordDTO {

    @NotEmpty(message = ErrorUtils.RESET_PASSWORD_INVALID_TOKEN)
    private String token;

    @NotEmpty(message = ErrorUtils.USER_PASSWORD_EMPTY)
    @Size(min = 10, max = 100)
    private String newPassword;

}
