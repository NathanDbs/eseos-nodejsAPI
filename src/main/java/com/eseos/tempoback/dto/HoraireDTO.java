package com.eseos.tempoback.dto;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * User DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class HoraireDTO {

    private Integer id;

    @NotEmpty(message = ErrorUtils.HORAIRE_DATE_EMPTY)
    private String date_planning;
    
    @NotEmpty(message = ErrorUtils.HORAIRE_CRENEAU_EMPTY)
    private String creneau;    
    
    @NotEmpty(message = ErrorUtils.HORAIRE_NBMEMBRES_EMPTY)
    private String nbmembres;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String lastmember;
}
