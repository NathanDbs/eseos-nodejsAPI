package com.eseos.tempoback.model.enums;

/**
 * Grade enumeration
 */
public enum  GradeEnum {
    ADMIN("ADMIN"),
    PREZ("PREZ"),
    VP("VP"),
    TREZ("TREZ"),
    RESPREZ("RESPREZ"),
    RESPCOM("RESPCOM"),
    REZ("REZ"),
    COM("COM"),
    MEMBRE("MEMBRE"),
    ALUMNI("ALUMNI"),
    CLIENT("CLIENT");


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
