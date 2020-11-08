package com.eseos.tempoback.repository;

import com.eseos.tempoback.model.Horaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * User repository
 */
@Repository
public interface HoraireRepository extends JpaRepository<Horaire, Integer> {

    /**
     * Find one horaire using the id
     * @param id the id
     * @return the horaire or an empty optional result
     */
    Optional<Horaire> findHoraireById(Integer id);

}
