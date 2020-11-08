package com.eseos.tempoback.service;

import com.eseos.tempoback.repository.HoraireRepository;
import com.eseos.tempoback.model.Horaire;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Horaire service
 */
@Service
@Slf4j
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
     * Find one horaire by his id
     * @param id the id of the horaire
     * @return an optional result with the horaire if the id exists
     * @ the exception if the horaire does not exists
     */
    public Optional<Horaire> findHoraireById(int id)  {
        return horaireRepository.findHoraireById(id);
    }

    /**
     * Check if a login is already use by one horaire
     * @param login the login to test
     * @return TRUE if the login already exists, else FALSE
     */
    public boolean horaireLoginExists(String login) {
        return this.horaireRepository.findHoraireById(Integer.parseInt(login)).isPresent();
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
     * Fin one horaire with id
     * @param id the identifier of the horaire
     * @return an optional result with the horaire if the id exists
     */
    public Optional<Horaire> findHoraireById(Integer id) {
        return this.horaireRepository.findById(id);
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
