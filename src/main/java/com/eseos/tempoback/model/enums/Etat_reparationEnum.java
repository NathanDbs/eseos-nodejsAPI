package com.eseos.tempoback.model.enums;

/**
 * etat reparation enumeration
 */
public enum  Etat_reparationEnum {
    ANNULEE("Annulée"),
    ACCEPTEE("Acceptée"),
    ENCOURS("En cours"),
    TERMINEE("Terminée"),
    PAIEMENT("Paiment");

    private final String etat;

    /**
     * Constructor
     * @param etat the etat
     */
    Etat_reparationEnum(String etat) {
        this.etat = etat;
    }

    /**
     * Get the etat
     * @return the etat
     */
    public String getEtat() {
        return this.etat;
    }
}
