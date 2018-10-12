package com.capybaras.donationtracker.models;

import android.content.Context;
import android.util.Log;

import com.capybaras.donationtracker.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mogedi on 10/12/2018.
 */

public class LocationList {

    private List<Location> locations = new ArrayList<>();

    public LocationList(Context ctx) {
        // Read the raw csv file
        InputStream is = ctx.getResources().openRawResource(R.raw.locationdata);

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
                Log.d("MyActivity","Lines: " + line);
                // use comma as separator columns of CSV
                String[] tokens = line.split(",");
                // Read the data
                Location location = new Location();

                // Setters
                location.setKey( Integer.parseInt(tokens[0]) );
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

                // Adding object to a class
                locations.add(location);

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

    public List<Location> getLocations() {
        return locations;
    }
}
