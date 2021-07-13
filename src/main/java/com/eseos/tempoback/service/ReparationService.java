package com.eseos.tempoback.service;

import com.eseos.tempoback.repository.ReparationRepository;
import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.model.Reparations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

/**
 * Repa service
 */
@Service
public class ReparationService {

    private final ReparationRepository reparationRepository;

    /**
     * Constructor
     * @param reparationRepository the user repository
     */
    @Autowired
    public ReparationService(ReparationRepository reparationRepository) {
        this.reparationRepository = reparationRepository;
    }

    /**
     * Find one user by his id
     * @param id the id of the user
     * @return an optional result with the user if the id exists
     * @throws UsernameNotFoundException the exception if the user does not exists
     */
    public Optional<Reparations> findRepaById(int id) throws UsernameNotFoundException {
        return reparationRepository.findRepaById(id);
    }

        /**
     * Find one user by his email
     * @param email the email of the user
     * @return an optional result with the user if the email exists
     * @throws UsernameNotFoundException the exception if the user does not exists
     */
    public Optional<Reparations> findRepaByReparateurEmail(String email) throws UsernameNotFoundException {
        return reparationRepository.findRepaByEmailReparateur(email);
    }

    /**
     * Save a user
     * @param user the user to save
     * @return the user saved
     */
    public Reparations saveRepa(Reparations repa) {
        return this.reparationRepository.save(repa);
    }

    /**
     * Find all users
     * @return the users
     */
    public List<Reparations> findAllRepas() {
        return this.reparationRepository.findAll();
    }

    /**
     * The user to delete
     * @param user the user to delete
     */
    public void deleteRepa(Reparations repa) {
        this.reparationRepository.delete(repa);
    }

        /**
     * Update the user
     * @param user the user to delete
     */
    public void updateRepa(Reparations repa) throws EntityNotFoundException {
        if(repa.getId() != null){
            Optional<Reparations> optionalRepa = this.reparationRepository.findById(repa.getId());
            if (optionalRepa.isPresent()) {
                this.reparationRepository.save(repa);
            }
        }else{
            throw new EntityNotFoundException(ErrorUtils.REPA_NOT_FOUND);
        }

    }

}
