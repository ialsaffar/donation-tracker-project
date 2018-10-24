package com.capybaras.donationtracker.models;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Model {
    /** Singleton instance */
    private static final Model instance = new Model();
    private static User loggedInUser;
    private static HashMap<String, User> users;
    private LocationList locationList;
    private List<Location> locations;
    private HashMap<Integer, Location> locationMap;

    public static Model getInstance() {
        return instance;
    }

    private Model() {
        users = new HashMap<>();
        users.put("user", new User("user", "pass", "abc@example.com", UserTypes.ADMIN));
        loggedInUser = null;
        locationList = new LocationList();
        locations = locationList.getLocations();
        locationMap = locationList.getLocationMap();
    }

    public void addUser(String username, String password, String email, UserTypes type){
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("username already exists");
        }
        User user = new User(username, password, email, type);
        users.put(username, user);
    }

    public boolean isUser(String username){
        return users.containsKey(username);
    }

    public boolean userPasswordMatch(String username, String password){
        return users.get(username).getPassword().equals(password);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public HashMap<Integer, Location> getLocationMap() {
        return locationMap;
    }


    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public static HashMap<String, User> getUsers() {
        return users;
    }

    // Utility method
    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }

    public void addUser(User newUser) {
        users.put(newUser.getUsername(), newUser);
    }
}
