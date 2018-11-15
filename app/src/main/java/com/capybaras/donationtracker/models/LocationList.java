package com.capybaras.donationtracker.models;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by mogedi on 10/12/2018.
 */

public class LocationList {

    private static List<Location> locations = new ArrayList<>();
    private static HashMap<Integer, Location> locationMap = new HashMap<>();

    /**
     * Location List constructor
     */
    public LocationList() {

        if (locations.isEmpty()) {

            // Read the raw csv file
            InputStream is = this.getClass().getClassLoader().getResourceAsStream("res/raw/locationdata.csv");

            // Reads text from character-input stream, buffering characters for efficient reading
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(is, Charset.forName("UTF-8"))
            );

            // Initialization
            String line = "";

            // Initialization
            try {
                // Step over headers
                reader.readLine();

                // If buffer is not empty
                while ((line = reader.readLine()) != null) {
                    Log.d("MyActivity", "Lines: " + line);
                    // use comma as separator columns of CSV
                    String[] tokens = line.split(",");
                    // Read the data
                    Location location = new Location();

                    // Setters
                    location.setKey(Integer.parseInt(tokens[0]));
                    location.setName(tokens[1]);
                    location.setLatitude(Double.parseDouble(tokens[2].trim()));
                    location.setLongitude(Double.parseDouble(tokens[3].trim()));
                    location.setStreetAddress(tokens[4]);
                    location.setCity(tokens[5]);
                    location.setState(tokens[6]);
                    location.setZipCode(Integer.parseInt(tokens[7]));
                    location.setType(tokens[8]);
                    location.setPhone(tokens[9]);
                    location.setWebsite(tokens[10]);
                    location.setItems(new ArrayList<Item>());
                    Location.setAllItems(new HashSet<Item>());

                    // Adding object to a class
                    locations.add(location);
                    locationMap.put(location.getKey(), location);

                    // Log the object
                    Log.d("My Activity", "Just created: " + locations);
                }

            } catch (IOException e) {
                // Logs error with priority level
                Log.wtf("MyActivity", "Error reading data file on line" + line, e);

                // Prints throwable details
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets the list of locations
     * @return the locations
     */
    public static List<Location> getLocations() {
        return locations;
    }

    /**
     * Gets the locations map
     * @return the set of key value pairs for Locations
     */
    public static HashMap<Integer, Location> getLocationMap() {
        return locationMap;
    }

    public static void addLocation(Location loc) {
        locations.add(loc);
    }

    /**
     * Saves location's info/list as a file
     * @param writer the print writer
     */
    public static void saveAsText (PrintWriter writer) {
        writer.println(locations.size());
        for (int i = 0; i < locations.size(); i++) {
            locations.get(i).saveAsText(writer);
        }
    }

    public static void loadFromText(BufferedReader reader) {
        locations.clear();
        locationMap.clear();
        try {
            String countStr = reader.readLine();
            assert countStr != null;
            int count = Integer.parseInt(countStr);

            for (int i = 0; i < count; ++i) {
                String line = reader.readLine();
                Location loc = Location.parseEntry(line);
                locations.add(loc);
                locationMap.put(loc.getKey(), loc);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}