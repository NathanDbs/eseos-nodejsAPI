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
 * Horaire entity
 */
@Entity
@Table(name = "planning" , schema = "public" )
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Horaire implements Serializable {

    @Id
    private int id_planning;

    @NotEmpty(message = ErrorUtils.HORAIRE_DATE_EMPTY)
    @Column(name = "datedebut")
    private String date_debut;
    
    @NotEmpty(message = ErrorUtils.HORAIRE_CRENEAU_EMPTY)
    @Column(name = "datefin")
    private String date_fin;    
    
    @NotEmpty(message = ErrorUtils.HORAIRE_MEMBRE_EMPTY)
    @Column(name = "membre")
    private String membre;
    
}
