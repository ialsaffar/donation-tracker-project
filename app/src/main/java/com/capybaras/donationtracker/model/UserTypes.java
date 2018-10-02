package com.capybaras.donationtracker.model;

public enum UserTypes {
    ADMIN("Administrator"),
    LOCATION_EMPLOYEE("Location Employee"),
    MANAGER("Manager");

    private final String userTypeNonCaps;

    UserTypes(String nonCaps) {
        this.userTypeNonCaps = nonCaps;
    }

    public String getNonCaps() {
        return this.userTypeNonCaps;
    }

    @Override
    public String toString() {
        return this.userTypeNonCaps;
    }

}
