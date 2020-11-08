package com.eseos.tempoback.model.enums;

/**
 * Authority enumeration
 */
public enum  AuthorityEnum {
    CONSULTANT("CONSULTANT"),
    COMMERCIAL("COMMERCIAL"),
    MANAGER("MANAGER"),
    ADMIN("ADMIN");

    private final String authority;

    /**
     * Constructor
     * @param authority the authority
     */
    AuthorityEnum(String authority) {
        this.authority = authority;
    }

    /**
     * Get the authority
     * @return the authority
     */
    public String getAuthority() {
        return this.authority;
    }
}
