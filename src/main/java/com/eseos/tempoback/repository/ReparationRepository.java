package com.eseos.tempoback.repository;

import com.eseos.tempoback.model.Reparations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repas repository
 */
@Repository
public interface ReparationRepository extends JpaRepository<Reparations, Integer> {

    /**
     * Find one repa using the id
     * @param id the id
     * @return the repa or an empty optional result
     */
    Optional<Reparations> findRepaById(Integer id);

       /**
     * Find one user using the reparateur's id
     * @param id the id
     * @return the repa or an empty optional result
     */
    Optional<Reparations> findRepaByEmailReparateur(String email_reparateur);

}
