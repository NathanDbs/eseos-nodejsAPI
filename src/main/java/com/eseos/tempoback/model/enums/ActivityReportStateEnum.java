package com.eseos.tempoback.model.enums;

/**
 * Activity report state enumeration
 */
public enum ActivityReportStateEnum {
    IN_PROGRESS("IN_PROGRESS"),
    REJECTED("REJECTED"),
    VALIDATED("VALIDATED"),
    SUBMITTED("SUBMITTED");

    private final String state;

    /**
     * Constructor
     * @param state the state
     */
    ActivityReportStateEnum(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

}
