package com.capybaras.donationtracker.models;

/**
 * UserTypes enum class
 */
public enum UserTypes {
    ADMIN("Administrator"),
    LOCATION_EMPLOYEE("Location Employee"),
    MANAGER("Manager");

    private final String userTypeNonCaps;

    UserTypes(String nonCaps) {
        this.userTypeNonCaps = nonCaps;
    }

    /**
     * Gets the user types in non caps
     * @return the user types
     */
    public String getNonCaps() {
        return this.userTypeNonCaps;
    }

    @Override
    public String toString() {
        return this.userTypeNonCaps;
    }

    /**
     * Gets the user types by name
     * @param name the specified name
     * @return the User type
     */
    public static UserTypes getByName(String name) {
        if ("Administrator".equals(name)) {
            return ADMIN;
        } else if ("Location Employee".equals(name)) {
            return LOCATION_EMPLOYEE;
        } else if ("Manager".equals(name)) {
            return MANAGER;
        } else {
            throw new IllegalArgumentException("No such UserType");
        }
    }
}
