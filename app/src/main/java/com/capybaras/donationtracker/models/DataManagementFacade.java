package com.capybaras.donationtracker.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * DataManagementFacade class: Singleton
 */
public class DataManagementFacade {
    public static final String ITEMS_FILE_NAME = "ItemData.txt";
    public static final String USERS_FILE_NAME = "UserData.txt";
    public static final String LOCATIONS_FILE_NAME = "LocationData.txt";

    private final Location loc;
    private final Model mod;

    private static final DataManagementFacade instance = new DataManagementFacade();

    private DataManagementFacade() {
        loc = new Location();
        mod = Model.getInstance();
    }

    /**
     * Gets the one single instance of the DataManagementFacade
     * class
     * @return the instance
     */
    public static DataManagementFacade getInstance() { return  instance; }

    /**
     * Loads an item from a specified file
     * @param file the specified file to start loading items from
     */
    public void loadItemText(File file) {
        try {
            BufferedReader itemReader = new BufferedReader(new FileReader(file));
            loc.loadFromText(itemReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads users from a file
     * @param file the file specified to start loading from
     */
    public void loadUserText(File file) {
        try {
            BufferedReader userReader = new BufferedReader(new FileReader(file));
            mod.loadFromText(userReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads locations from a specified file
     * @param file the specified file to start loading from
     * @return true if the locations were loaded, false if otherwise
     */
    public boolean loadLocationText(File file) {
        try {
            BufferedReader locationReader = new BufferedReader(new FileReader(file));
            LocationList.loadFromText(locationReader);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    /**
     * Saves item information into a file
     * @param file the file to save all info to
     */
    public void saveItemText(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            loc.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves user information to a specified file
     * @param file the file to save info to
     */
    public void saveUserText(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            mod.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Saves location info into a specified file
     * @param file the file to save info into
     * @return true if the location information was saved, false if otherwise
     */
    public boolean saveLocationText(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            LocationList.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
