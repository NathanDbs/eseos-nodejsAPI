package com.eseos.tempoback.repository;

import com.eseos.tempoback.model.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * User repository
 */
@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Integer> {

    /**
     * Find one horaire using the member
     * @param member the email of the member 
     * @return the horaire or an empty optional result
     */
    Optional<Horaire> findHoraireByMembre(String membre);


    /**
     * Find all Horaires
     * @
     * @return a list of horaires
     */
    List<Horaire> findAll();

}
