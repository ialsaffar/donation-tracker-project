package com.capybaras.donationtracker.models;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;

public class DataManagementFacade {
    public final static String ITEMS_FILE_NAME = "ItemData.txt";
    public final static String USERS_FILE_NAME = "UserData.txt";
    public final static String LOCATIONS_FILE_NAME = "LocationData.txt";

    private Location loc;
    private Model mod;
    private LocationList locList;

    private static DataManagementFacade instance = new DataManagementFacade();

    private DataManagementFacade() {
        loc = new Location();
        mod = Model.getInstance();
        locList = new LocationList();
    }

    public static DataManagementFacade getInstance() { return  instance; }

    public boolean loadItemText(File file) {
        try {
            BufferedReader itemReader = new BufferedReader(new FileReader(file));
            loc.loadFromText(itemReader);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    public boolean loadUserText(File file) {
        try {
            BufferedReader userReader = new BufferedReader(new FileReader(file));
            mod.loadFromText(userReader);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    public boolean loadLocationText(File file) {
        try {
            BufferedReader locationReader = new BufferedReader(new FileReader(file));
            locList.loadFromText(locationReader);
        } catch (FileNotFoundException e) {
            return false;
        }

        return true;
    }

    public boolean saveItemText(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            loc.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveUserText(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            mod.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean saveLocationText(File file) {
        try {
            PrintWriter pw = new PrintWriter(file);
            locList.saveAsText(pw);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
