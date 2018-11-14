package com.capybaras.donationtracker.models;

import android.app.Application;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Model class (Singleton)
 */
public class Model extends Application{
    /** Singleton instance */
    private static final Model instance = new Model();
    private static User loggedInUser;
    private static HashMap<String, User> users;
    private static List<User> userList;
    private LocationList locationList;
    private List<Location> locations;
    private HashMap<Integer, Location> locationMap;

    /**
     * Gets the one instance of the model
     * @return the Model
     */
    public static Model getInstance() {
        return instance;
    }

    /**
     * Default Constructor for the Model
     */
    private Model() {
        users = new HashMap<>();
        userList = new ArrayList<>();
        users.put("user", new User("user", "pass", "abc@example.com", UserTypes.ADMIN));
        loggedInUser = null;
        locationList = new LocationList();
        locations = locationList.getLocations();
        locationMap = locationList.getLocationMap();

    }

    /**
     * Adds a User
     * @param username the user's username
     * @param password the user's password
     * @param email the user's email
     * @param type the user's type
     */
    public void addUser(String username, String password, String email, UserTypes type){
        if (username == null) {
            throw new IllegalArgumentException("The username cannot be null.");
        }
        if (password == null) {
            throw new IllegalArgumentException("The password cannot be null.");
        }
        if (email == null) {
            throw new IllegalArgumentException("The email cannot be null.");
        }
        if (type == null) {
            throw new IllegalArgumentException("The userType cannot be null.");
        }
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("username already exists");
        }
        User user = new User(username, password, email, type);
        userList.add(user);
        users.put(username, user);
    }

    /**
     * Determines if the specified user is already registered
     * @param username the user's username
     * @return true if the user is already registered, false if otherwise
     */
    public boolean isUser(String username){
        return users.containsKey(username);
    }

    /**
     * Checks to make sure the specified username and it's password match
     * @param username the user's username
     * @param password the user's password
     * @return true if they belong to the same account, false if the password
     *         does not match the user's account
     */
    public boolean userPasswordMatch(String username, String password){
        return users.get(username).getPassword().equals(password);
    }

    /**
     * Gets a list of all locations
     * @return the list of locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Gets the Locations through key value pairs (location map)
     * @return the location map
     */
    public HashMap<Integer, Location> getLocationMap() {
        return locationMap;
    }

    /**
     * Sets the current user to being logged in
     * @param user the logged in user
     */
    public void setLoggedInUser(User user) {
        loggedInUser = user;
    }

    /**
     * Gets the logged in user
     * @return the User who is logged in
     */
    public User getLoggedInUser() {
        return loggedInUser;
    }

    /**
     * Gets all the users
     * @return the users (a map of users)
     */
    public static HashMap<String, User> getUsers() {
        return users;
    }

    /**
     * Gets the list of users
     * @return the list of users (List)
     */
    public static List<User> getUserList() { return userList; }



    /**
     * Utility method
     * @param values the specified values to get their hash codes
     * @return an array of the hash code's of the specified values
     */
    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }

    /**
     * adds a user by a user object
     * @param newUser the new User
     */
    public void addUser(User newUser) {
        if (newUser == null) {
            throw new IllegalArgumentException("The new user cannot be null.");
        }

        userList.add(newUser);
        users.put(newUser.getUsername(), newUser);
    }

    /**
     * Gets the location by their key
     * @param key the key of the location
     * @return the desired Location
     */
    public Location getLocationByKey(int key) {
        for (Location l: locations) {
            if (key == l.getKey()) {
                return l;
            }
        }
        throw new NoSuchElementException("No location with key: " + key);
    }

    /**
     * Saves the user list as a text
     * @param writer the print writer
     */
    public void saveAsText(PrintWriter writer) {
        writer.println(userList.size());
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).saveAsText(writer);
        }
    }

    /**
     * Loads the user list from the text
     * @param reader the reader to read in the users
     */
    public void loadFromText(BufferedReader reader) {
        userList.clear();
        users.clear();

        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                reader.readLine();
                String line2 = reader.readLine();
                User use = User.parseEntry(line, line2);
                userList.add(use);
                users.put(use.getUsername(), use);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
