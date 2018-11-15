package com.capybaras.donationtracker.models;

import android.app.Application;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Created by mogedi on 10/11/2018.
 */

public class Location extends Application{

    private static final int TOKENS_LENGTH = 11;
    private int key;
    private String name;
    private double latitude;
    private double longitude;
    private String streetAddress;
    private String city;
    private String state;
    private int zipCode;
    private String type;
    private String phone;
    private String website;
    private static List<Item> items = new ArrayList<>();

    /**
     * Default constructor
     */
    public Location(){}

    /**
     * Default constructor with params
     * @param key the key of the location
     * @param name the name of the location
     * @param latitude the latitude of location
     * @param longitude the longitude of the location
     * @param streetAddress the street address of the location
     * @param city the city of the location
     * @param state the state of the location
     * @param zipCode the zip code of the location
     * @param type the type of the location
     * @param phone the phone of the location
     * @param website the website of the location
     */
    public Location(int key,
                    String name,
                    double latitude,
                    double longitude,
                    String streetAddress,
                    String city,
                    String state,
                    int zipCode,
                    String type,
                    String phone,
                    String website) {
        this.key = key;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.streetAddress = streetAddress;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.type = type;
        this.phone = phone;
        this.website = website;
    }

    /**
     * Adds an item
     * @param item the specified item
     */
    public void addItem(Item item) {
        items.add(item);
    }

    /**
     * Gets the item by the specified ID
     * @param id the ID of the desired item
     * @return the item
     */
    public Item getItemById(int id) {
        for (Item i: items) {
            if (i.getId() == id) {
                return i;
            }
        }
        throw new NoSuchElementException("No item with ID: " + id + "in location: " + this.name);
    }

    /**
     * Gets the key of the location
     * @return the key
     */
    public int getKey() {
        return key;
    }

    /**
     * Sets the key of the location
     * @param key the new key
     */
    public void setKey(int key) {
        this.key = key;
    }

    /**
     * Gets the name of the location
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the location
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the latitude of the location
     * @return the latitude
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Sets the latitude of the location
     * @param latitude the new latitude
     */
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    /**
     * Gets the longitude of the location
     * @return the longitude
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Sets the longitude of the location
     * @param longitude the new longitude
     */
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    /**
     * Gets the street address of the location
     * @return the street address
     */
    public String getStreetAddress() {
        return streetAddress;
    }

    /**
     * Sets a new street address for the location
     * @param streetAddress the new street address
     */
    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    /**
     * Gets the city of the location
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city of the location
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the state of the location
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the state of the location
     * @param state the new state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Gets the zip code of the location
     * @return the zip code
     */
    public int getZipCode() {
        return zipCode;
    }

    /**
     * sets the zip code of the location
     * @param zipCode the new zip code
     */
    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * Gets the type of the location
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the type of the location
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * gets the phone number of the location
     * @return the phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the location
     * @param phone the new phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the website of the location
     * @return the website
     */
    public String getWebsite() {
        return website;
    }

    /**
     * sets the website of the location
     * @param website the new website
     */
    public void setWebsite(String website) {
        this.website = website;
    }

    /**
     * Gets the items at the location
     * @return the list of items
     */
    public static List<Item> getItems() {
        return items;
    }

    /**
     * Sets/updates the list of items at the location
     * @param items the new list of items
     */
    public static void setItems(List<Item> items) {
        Location.items = items;
    }

//    @Override
//    public String toString() {
//        return "";
//    }

    /**
     * Parses through a line and returns the location with
     * all the information
     * @param line the line to parse through
     * @return the Location object
     */
    public static Location parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == TOKENS_LENGTH;

        Location fromFile = new Location(parseInt(tokens[0]),
                tokens[1],
                parseDouble(tokens[2]),
                parseDouble(tokens[3]),
                tokens[4],
                tokens[5],
                tokens[6],
                parseInt(tokens[7]),
                tokens[8],
                tokens[9],
                tokens[10]);

        return fromFile;
    }

    /**
     * Saves a location with all its information
     * as a text
     * @param writer the print writer
     */
    public void saveAsText(PrintWriter writer) {
        writer.println("With");
        writer.println(key + "\t" + name + "\t" + latitude + "\t" + longitude + "\t" + streetAddress + "\t" + city + "\t" + state + "\t" + zipCode + "\t" + type + "\t" + phone + "\t" + website);
        writer.println(Item.getNumberOfItems());
        writer.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            items.get(i).saveAsText(writer);
        }
    }

    /**
     * Saves the Location without items
     * @param writer the print writer
     */
    public void saveAsTextSansItems(PrintWriter writer) {
        writer.println("Without");
        writer.println(key + "\t" + name + "\t" + latitude + "\t" + longitude + "\t" + streetAddress + "\t" + city + "\t" + state + "\t" + zipCode + "\t" + type + "\t" + phone + "\t" + website);
    }

    /**
     * Loads the location from a text
     * @param reader the reader to take in the info
     */
    public void loadFromText(BufferedReader reader) {
        System.out.println("I'm here!");

        try {
            String nextLine = reader.readLine();
            String locLine = reader.readLine();
            Location.parseEntry(locLine);

            if ("With".equals(nextLine)) {
                String numItems = reader.readLine();
                Item.setNumberOfItems(Integer.parseInt(numItems));

                String countStr = reader.readLine();
                assert countStr != null;
                int count = Integer.parseInt(countStr);

                items.clear();

                for (int i = 0; i < count; i++) {
                    String line = reader.readLine();
                    reader.readLine();
                    Item item = Item.parseEntry(line, reader.readLine());
                    items.add(item);
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) {
            return false;
        }

        if(o == this) {
            return true;
        }

        if( !(o instanceof Location) ) {
            return false;
        }

        Location other = (Location) o;

        boolean equality = this.key == other.key
                && this.name.equals(other.name)
                && this.latitude == other.latitude
                && this.longitude == other.longitude
                && this.streetAddress.equals(other.streetAddress)
                && this.city.equals(other.city)
                && this.state.equals(other.state)
                && this.zipCode == other.zipCode
                && this.type.equals(other.type)
                && this.phone.equals(other.phone)
                && this.website.equals(other.website);

        return equality;
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + name.hashCode();
        result = 31 * result + zipCode;
        result = 31 * result + streetAddress.hashCode();
        return result;
    }

}
