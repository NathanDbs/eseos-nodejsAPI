package com.eseos.tempoback.dto;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.*;

/**
 * Reparations DTO
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class reparationsDTO {

    private Integer id;

    @NotEmpty(message = ErrorUtils.REPA_REPARATEUR_EMPTY)
    private Integer id_reparateur;

    @NotEmpty(message = ErrorUtils.REPA_CLIENT_EMPTY)
    private Integer id_client;

    @NotEmpty(message = ErrorUtils.REPA_ETAT_REPARATION_EMPTY)
    private Integer etat_reparation;

    @NotEmpty(message = ErrorUtils.REPA_ETAT_DEVIS_EMPTY)
    private Integer etat_devis;

    private String devis;
}
