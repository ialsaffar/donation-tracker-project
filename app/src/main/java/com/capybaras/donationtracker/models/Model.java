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

public class Model extends Application{
    /** Singleton instance */
    private static final Model instance = new Model();
    private static User loggedInUser;
    private static HashMap<String, User> users;
    private static List<User> userList;
    private LocationList locationList;
    private List<Location> locations;
    private HashMap<Integer, Location> locationMap;

    public static Model getInstance() {
        return instance;
    }

    public Model() {
        users = new HashMap<>();
        userList = new ArrayList<>();
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
        userList.add(user);
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

    public static List<User> getUserList() { return userList; }

    // Utility method
    public static int hash(Object... values) {
        return Arrays.hashCode(values);
    }

    public void addUser(User newUser) {
        userList.add(newUser);
        users.put(newUser.getUsername(), newUser);
    }

    public Location getLocationByKey(int key) {
        for (Location l: locations) {
            if (key == l.getKey()) {
                return l;
            }
        }
        throw new NoSuchElementException("No location with key: " + key);
    }

    public void saveAsText(PrintWriter writer) {
        writer.println(userList.size());
        for (int i = 0; i < userList.size(); i++) {
            userList.get(i).saveAsText(writer);
        }
    }

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
