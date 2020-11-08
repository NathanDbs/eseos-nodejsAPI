package com.eseos.tempoback.model;

import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.validator.constraint.AuthorityConstraint;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Authority entity
 */
@Entity
@Table(name = "t_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Authority implements Serializable {

    @NotNull(message = ErrorUtils.AUTHORITY_NAME_NULL)
    @Size(min = 1, max = 50, message = ErrorUtils.AUTHORITY_NAME_SIZE)
    @AuthorityConstraint
    @Id
    private String name;

}


