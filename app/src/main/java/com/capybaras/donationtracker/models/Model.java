package com.capybaras.donationtracker.models;

import java.util.HashMap;
import java.util.List;

public class Model {
    /** Singleton instance */
    private static final Model _instance = new Model();
    public static Model getInstance() { return _instance; }

    private static HashMap<String, String> users = new HashMap<>();

    public static HashMap<String, String> getUsers() {
        return users;
    }

    public void addUser(String user, String password){
        users.put(user, password);
    }

    public boolean isUser(String user){
        return users.containsKey(user);
    }

    public boolean userPasswordMatch(String user, String password){
        return users.get(user).equals(password);
    }

    LocationList locationList = new LocationList();
    private List<Location> locations = locationList.getLocations();
    public List<Location> getLocations() {
        return locations;
    }
    private HashMap<Integer, Location> locationMap = locationList.getLocationMap();
    public HashMap<Integer, Location> getLocationMap() {
        return locationMap;
    }

}
