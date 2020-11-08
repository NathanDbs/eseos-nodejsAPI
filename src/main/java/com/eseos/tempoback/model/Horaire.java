package com.eseos.tempoback.model;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * Horaire entity
 */
@Entity
@Table(name = "planning")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planning")
    private int id;

    @NotEmpty(message = ErrorUtils.HORAIRE_DATE_EMPTY)
    @Column(name = "date_planning")
    private String date_planning;
    
    @NotEmpty(message = ErrorUtils.HORAIRE_CRENEAU_EMPTY)
    @Column(name = "creneau")
    private String creneau;    
    
    @NotEmpty(message = ErrorUtils.HORAIRE_NBMEMBRES_EMPTY)
    @Column(name = "nbmembres")
    private String nbmembres;
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "lastmember")
	private String lastmember;
}
