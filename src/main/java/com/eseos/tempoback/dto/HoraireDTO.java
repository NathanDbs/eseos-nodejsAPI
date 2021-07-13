package com.eseos.tempoback.dto;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

import javax.validation.constraints.*;

/**
 * User DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HoraireDTO {

    @NotEmpty(message = ErrorUtils.HORAIRE_DATE_EMPTY)
    private Date date_debut;

    @NotEmpty(message = ErrorUtils.HORAIRE_DATE_EMPTY)
    private Date date_fin; 
    
    @NotEmpty(message = ErrorUtils.HORAIRE_MEMBRE_EMPTY)
    private String membre;
    
}
