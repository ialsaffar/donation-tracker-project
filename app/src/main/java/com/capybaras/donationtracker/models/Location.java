package com.capybaras.donationtracker.models;

import android.app.Application;
import android.content.Context;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Created by mogedi on 10/11/2018.
 */

public class Location extends Application{

    private static final String FILE_NAME = "LocationData.txt";
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
    private List<Item> items = new ArrayList<>();

    public Location() {
//        try {
//            ObjectInputStream pleaseOpen = new ObjectInputStream(new FileInputStream(FILE_NAME));
//            items = (List<Item>) pleaseOpen.readObject();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }

    public void addItem(Item item) {
        items.add(item);
//        try {
//            FileOutputStream pleaseWork = getApplicationContext().openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
//            ObjectOutputStream pleaseWorkOut = new ObjectOutputStream(pleaseWork);
//            pleaseWorkOut.writeObject(items);
//            pleaseWorkOut.close();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
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

}
