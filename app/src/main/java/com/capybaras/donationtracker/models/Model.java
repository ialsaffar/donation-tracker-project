package com.capybaras.donationtracker.models;

import android.app.Application;
import android.content.Context;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;

public class Model extends Application{
    /** Singleton instance */
    private static final Model instance = new Model();
    private static final String FILE_NAME = "ModelData.txt";
    private static User loggedInUser;
    private static HashMap<String, User> users;
    private LocationList locationList;
    private List<Location> locations;
    private HashMap<Integer, Location> locationMap;

    public static Model getInstance() {
        return instance;
    }

    public Model() {
        users = new HashMap<>();
        users.put("user", new User("user", "pass", "abc@example.com", UserTypes.ADMIN));
        loggedInUser = null;
        locationList = new LocationList();
        locations = locationList.getLocations();
        locationMap = locationList.getLocationMap();

//        try {
//            ObjectInputStream pleaseOpen = new ObjectInputStream(new FileInputStream(FILE_NAME));
//            users = (HashMap<String, User>) pleaseOpen.readObject();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void addUser(String username, String password, String email, UserTypes type){
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("username already exists");
        }
        User user = new User(username, password, email, type);
        users.put(username, user);

//        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
//            File pleaseWork = new File(Environment.getExternalStoragePublicDirectory(
//                    Environment.DIRECTORY_DOCUMENTS), FILE_NAME);
//        }
//        try {
//            FileOutputStream pleaseWork = getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream pleaseWorkOut = new ObjectOutputStream(pleaseWork);
//            pleaseWorkOut.writeObject(users);
//            pleaseWorkOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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
//        try {
//            FileOutputStream pleaseWork = getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream pleaseWorkOut = new ObjectOutputStream(pleaseWork);
//            pleaseWorkOut.writeObject(users);
//            pleaseWorkOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public Location getLocationByKey(int key) {
        for (Location l: locations) {
            if (key == l.getKey()) {
                return l;
            }
        }
        throw new NoSuchElementException("No location with key: " + key);
    }
}
