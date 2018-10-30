package com.capybaras.donationtracker.models;

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

    public static UserTypes getByName(String name) {
        if (name.equals("Administrator")) {
            return ADMIN;
        } else if (name.equals("Location Employee")) {
            return LOCATION_EMPLOYEE;
        } else if (name.equals("Manager")) {
            return MANAGER;
        } else {
            throw new IllegalArgumentException("No such UserType");
        }
    }
}
