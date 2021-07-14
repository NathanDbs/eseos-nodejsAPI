package com.eseos.tempoback.model;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.eseos.tempoback.model.enums.GradeEnum;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * User entity
 */
@Entity
@Table(name = "utilisateurs",schema = "public")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User implements Serializable {

    @Id
    @NotEmpty(message = ErrorUtils.USER_EMAIL_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_EMAIL_SIZE)
    @Email(message = ErrorUtils.USER_EMAIL_FORMAT)
    @Column(name = "mail")
    private String email;

    @NotEmpty(message = ErrorUtils.USER_PASSWORD_EMPTY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Size(min = 10, max = 100)
    @Column(name = "_password")
    private String password;

    @NotEmpty(message = ErrorUtils.USER_FIRST_NAME_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_FIRST_NAME_SIZE)
    @Column(name = "firstname")
    private String firstName;

    @NotEmpty(message = ErrorUtils.USER_LAST_NAME_EMPTY)
    @Size(max = 255, message = ErrorUtils.USER_LAST_NAME_SIZE)
    @Column(name = "lastname")
    private String lastName;

    @NotEmpty(message = ErrorUtils.USER_GRADE_EMPTY)
    @Column(name = "grade")
    @Enumerated(EnumType.STRING)
    private GradeEnum grade;

    private String picture;

    public boolean isAdmin(){
        return (getGrade().getGrade() == "PREZ" 
        || getGrade().getGrade() == "VP" 
        || getGrade().getGrade() == "TREZ" 
        || getGrade().getGrade() == "RESPREZ" 
        || getGrade().getGrade() == "RESPCOM" 
        || getGrade().getGrade() == "ALUMNI" 
        || getGrade().getGrade() == "ADMIN");
    }

    /** Permission pour tous ceux qui peuvent ajouter et supprimer un creneau ainsi que modifier un devis
    * @return boolean 
    */
    public boolean canAddOrDeleteCreneauAndUpdateDevis(){
        return (getGrade().getGrade() == "PREZ" 
        || getGrade().getGrade() == "VP" 
        || getGrade().getGrade() == "TREZ" 
        || getGrade().getGrade() == "RESPREZ" 
        || getGrade().getGrade() == "RESPCOM" 
        || getGrade().getGrade() == "REZ" 
        || getGrade().getGrade() == "COM" 
        || getGrade().getGrade() == "MEMBRE" 
        || getGrade().getGrade() == "ADMIN");
    }

    /** Permission de voir tous les devis
    * @return boolean 
    */
    public boolean canSeeAllDevis(){
        return (getGrade().getGrade() == "PREZ" 
        || getGrade().getGrade() == "VP" 
        || getGrade().getGrade() == "TREZ" 
        || getGrade().getGrade() == "RESPREZ" 
        || getGrade().getGrade() == "RESPCOM" 
        || getGrade().getGrade() == "REZ" 
        || getGrade().getGrade() == "COM" 
        || getGrade().getGrade() == "CLIENT"
        || getGrade().getGrade() == "MEMBRE" 
        || getGrade().getGrade() == "ADMIN");
    }


    /** Permission de :
     *  - Supprimer un devis
     *  - Ajouter / Supprimer un membre
     *  - Promouvoir ou retrograder un Resp
     * @return boolean 
    */
    public boolean isPrezOrVP(){
        return (getGrade().getGrade() == "PREZ" 
        || getGrade().getGrade() == "VP" 
        || getGrade().getGrade() == "ADMIN");
    }

    /** Permission de :
     *  - Promouvoir ou retrograder le bureau restreint
     * @return boolean 
    */
    public boolean isPrez(){
        return (getGrade().getGrade() == "PREZ");
    }

}
