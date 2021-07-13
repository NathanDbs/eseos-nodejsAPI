package com.eseos.tempoback.model.enums;

/**
 * etat devis enumeration
 */
public enum  Etat_devisEnum {
    ENVOYE("Envoyé"),
    ACCEPTE("Accepté"),
    REFUSE("Refusé");

    private final String etat;

    /**
     * Constructor
     * @param etat the etat
     */
    Etat_devisEnum(String etat) {
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
