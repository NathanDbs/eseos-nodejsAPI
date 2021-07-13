package com.eseos.tempoback.model.enums;

/**
 * Grade enumeration
 */
public enum  GradeEnum {
    ADMIN("Admin"),
    PRESIDENT("Président"),
    VP("Vice-président"),
    TRESORIER("Trésorier"),
    RESP_RESEAU("Resp Réseau"),
    RESP_COM("Resp Com"),
    RESEAU("Membre Réseau"),
    COM("Membre Com"),
    MEMBRE("Membre"),
    ALUMNI("Alumni"),
    CLIENT("Client");


    private final String grade;

    /**
     * Constructor
     * @param grade the grade
     */
    GradeEnum(String grade) {
        this.grade = grade;
    }

    /**
     * Get the grade
     * @return the grade
     */
    public String getGrade() {
        return this.grade;
    }
}
