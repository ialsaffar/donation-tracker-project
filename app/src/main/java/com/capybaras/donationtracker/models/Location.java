package com.capybaras.donationtracker.models;

import android.app.Application;
import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

/**
 * Created by mogedi on 10/11/2018.
 */

public class Location extends Application{

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

    public Location(){}

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
                    String website,
                    List<Item> items) {
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
        this.items = items;
    }

    public void addItem(Item item) {}

    public Item getItemById(int id) {
        for (Item i: items) {
            if (i.getId() == id) {
                return i;
            }
        }
        throw new NoSuchElementException("No item with ID: " + id + "in location: " + this.name);
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "";
    }

    public static Location parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t\t");
        assert tokens.length >= 11;

        List<Item> placeHolder = new ArrayList<>();

        for(int i = 11; i < tokens.length; i++) {
            placeHolder.add(Item.parseEntry(tokens[i]));
        }

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
                tokens[10],
                placeHolder);

        return fromFile;
    }

    public void saveAsText(PrintWriter writer) {
        StringBuilder itemsList = new StringBuilder();
        for (int i = 0; i < items.size(); i++) {
            Item cI = items.get(i);
            itemsList.append(" + \t\t + " + cI.getId() + "\t" + cI.getName() + "\t" + cI.getTimeStamp() + "\t" + cI.getLocation().getPhone() + "\t" + cI.getCreator() + "\t" + cI.getShortDescription() + "\t" + cI.getFullDescription() + "\t" + cI.getCents() + "\t" + cI.getCategory().getCategoryName());
        }
        writer.println(key + " + \t\t + " + name + " + \t\t + " + latitude + " + \t\t + " + longitude + " + \t\t + " + streetAddress + " + \t\t + " + city + " + \t\t + " + state + " + \t\t + " + zipCode + " + \t\t + " + type + " + \t\t + " + phone + " + \t\t + " + website + " + \t\t + " + itemsList);
    }

    public void loadFromText(BufferedReader reader) {
        items.clear();

        try {
            Location.parseEntry(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
