package com.eseos.tempoback.service;

import com.eseos.tempoback.repository.HoraireRepository;
import com.eseos.tempoback.model.Horaire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Horaire service
 */
@Service
public class HoraireService {

    private final HoraireRepository horaireRepository;

    /**
     * Constructor
     * @param horaireRepository the horaire repository
     */
    @Autowired
    public HoraireService(HoraireRepository horaireRepository) {
        this.horaireRepository = horaireRepository;
    }

    /**
     * Save a horaire
     * @param horaire the horaire to save
     * @return the horaire saved
     */
    public Horaire saveHoraire(Horaire horaire) {
        return this.horaireRepository.save(horaire);
    }

    /**
     * Find all horaires
     * @return the horaires
     */
    public List<Horaire> findAllHoraires() {
        return this.horaireRepository.findAll();
    }

    /**
     * Find one horaire
     * @return horaire
     */
    public Optional<Horaire> findHoraire(String email) {
        return this.horaireRepository.findHoraireByMembre(email);
    }

    /**
     * The horaire to delete
     * @param horaire the horaire to delete
     */
    public void deleteHoraire(Horaire horaire) {
        this.horaireRepository.delete(horaire);
    }

    /**
     * Check if a horaire can be deleted
     * @param horaire the horaire to check
     * @return TRUE if the horaire can be delete, else FALSE
     */
    public boolean canDeleteHoraire(Horaire horaire) {
        return true;
    }
}
