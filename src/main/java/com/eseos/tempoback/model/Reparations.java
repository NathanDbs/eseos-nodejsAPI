package com.eseos.tempoback.model;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Reparation entity
 */
@Entity
@Table(name = "reparations",schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reparations implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repa")
    private Integer id;

    @NotEmpty(message = ErrorUtils.REPA_REPARATEUR_EMPTY)
    @Column(name = "reparateur")
    private String emailReparateur;

    @NotEmpty(message = ErrorUtils.REPA_CLIENT_EMPTY)
    @Column(name = "client")
    private Integer id_client;

    @NotEmpty(message = ErrorUtils.REPA_ETAT_REPARATION_EMPTY)
    private Integer etat_reparation;

    @NotEmpty(message = ErrorUtils.REPA_ETAT_DEVIS_EMPTY)
    private Integer etat_devis;

    private String devis;
}
