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

    public static final int TOKENS_LENGTH = 11;
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

    public void addItem(Item item) {
        items.add(item);
    }

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

    public static List<Item> getItems() {
        return items;
    }

    public static void setItems(List<Item> items) {
        Location.items = items;
    }

//    @Override
//    public String toString() {
//        return "";
//    }

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

    public void saveAsText(PrintWriter writer) {
        writer.println("With");
        writer.println(key + "\t" + name + "\t" + latitude + "\t" + longitude + "\t" + streetAddress + "\t" + city + "\t" + state + "\t" + zipCode + "\t" + type + "\t" + phone + "\t" + website);
        writer.println(items.size());
        for (int i = 0; i < items.size(); i++) {
            items.get(i).saveAsText(writer);
        }
    }

    public void saveAsTextSansItems(PrintWriter writer) {
        writer.println("Without");
        writer.println(key + "\t" + name + "\t" + latitude + "\t" + longitude + "\t" + streetAddress + "\t" + city + "\t" + state + "\t" + zipCode + "\t" + type + "\t" + phone + "\t" + website);
    }

    public void loadFromText(BufferedReader reader) {
        System.out.println("I'm here!");

        try {
            String nextLine = reader.readLine();
            String locLine = reader.readLine();
            Location.parseEntry(locLine);

            if (nextLine.equals("With")) {
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

}
