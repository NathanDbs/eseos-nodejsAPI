package com.eseos.tempoback.controller;

import java.security.Principal;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import com.eseos.tempoback.dto.HoraireDTO;
import com.eseos.tempoback.errorhandling.ErrorUtils;
import com.eseos.tempoback.exceptions.RelatedDataException;
import com.eseos.tempoback.exceptions.UniqueFieldException;
import com.eseos.tempoback.model.Horaire;
import com.eseos.tempoback.model.User;
import com.eseos.tempoback.service.HoraireService;
import com.eseos.tempoback.service.UserService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller for horaires
 */
@RestController
@RequestMapping(value = "/horaire")
public class HoraireController {

    @Value( "1800" )
    private int resetPasswordExpirationTime;

    @Autowired
    private HoraireService horaireService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    /**
     * Voir si le club est ouvert
     * @return a boolean 
     */
    @GetMapping(value = "/isOpen", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public boolean isOpen() {         
        return !this.horaireService.findAllHoraires()
                .stream()
                .map(horaire -> modelMapper.map(horaire, HoraireDTO.class))
                .filter(horaire -> (horaire.getDate_debut().compareTo(new Date()) < 0) && (horaire.getDate_fin().compareTo(new Date()) > 0))
                .collect(Collectors.toList()).isEmpty();
    }

    /**
     * Ajoute un créneau
     * @param horaireDTO le creaneau a ajouter
     * @return un httpStatus created ou notFound
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus addHoraire(@Valid @RequestBody HoraireDTO horaireDTO, Principal principal) throws UniqueFieldException {
        try{
            Optional<User> potentialUser = this.userService.findUserByEmail(horaireDTO.getMembre());
            Optional<User> principalUser = this.userService.findUserByEmail(principal.getName());
            if(principalUser.get().canAddOrDeleteCreneauAndUpdateDevis()){
                if(!potentialUser.isPresent()){throw new EntityNotFoundException(ErrorUtils.USER_NOT_FOUND);}
                this.horaireService.saveHoraire(modelMapper.map(horaireDTO, Horaire.class));
                return HttpStatus.CREATED;
            }else{
                throw new Error();
            }
        }catch(Error e){
            return HttpStatus.NOT_FOUND;
        }
    }

    /**
     * Supprime un creneau
     * @param creaneau le creneau
     * @throws RelatedDataException an exception if the user is related with others datas
     */
    @DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCreaneau(@Valid @RequestBody HoraireDTO horaireDTO, Principal principal) throws RelatedDataException {
        Optional<Horaire> potentialHoraire = this.horaireService.findHoraire(horaireDTO.getMembre());

        Optional<User> principalUser = this.userService.findUserByEmail(principal.getName());
        if(principalUser.get().canAddOrDeleteCreneauAndUpdateDevis()){
            if (potentialHoraire.isEmpty()) {
                throw new EntityNotFoundException(ErrorUtils.HORAIRE_NOT_FOUND);
            }

            final Horaire horaire = potentialHoraire.get();

            this.horaireService.deleteHoraire(horaire);
        }else{
            throw new RelatedDataException(ErrorUtils.DELETE_ADD_HORAIRE_FORBIDDEN);
        }
    }

}
