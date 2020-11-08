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
 * User entity
 */
@Entity
@Table(name = "comptes")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = ErrorUtils.USER_PASSWORD_EMPTY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 10, max = 100)
    @Column(name = "mdp")
    private String password;

    @NotEmpty(message = ErrorUtils.USER_FIRST_NAME_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_FIRST_NAME_SIZE)
    @Column(name = "prenom")
    private String firstName;

    @NotEmpty(message = ErrorUtils.USER_LAST_NAME_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_LAST_NAME_SIZE)
    @Column(name = "nom")
    private String lastName;

    @NotEmpty(message = ErrorUtils.USER_EMAIL_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_EMAIL_SIZE)
    @Email(message = ErrorUtils.USER_EMAIL_FORMAT)
    @Column(name = "mail")
    private String email;

    private String commentaire;

    private String rang;

    private int nbprestation;

    private int nbprobleme;

    private int nbachat;
}
