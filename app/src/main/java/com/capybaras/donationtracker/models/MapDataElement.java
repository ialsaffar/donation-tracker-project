package com.capybaras.donationtracker.models;

public class MapDataElement {

    private String name;
    private String description;
    private Location location;


    public MapDataElement(String name, String description, Location location) {
        this.name = name;
        this.description = description;
        this.location = location;
    }

    @Override
    public String toString() {
        return this.name + "\n" + this.description;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return location.getLatitude();
    }

    public double getLongitude() {
        return location.getLongitude();
    }

    public String getDescription() {
        return description;
    }

}
