package com.capybaras.donationtracker.models;

import java.util.Date;

public class Item {
    private static int numberOfItems;

    private int id;
    private String name;
    private Date timeStamp;
    private Location location;
    private String creator;
    private String shortDescription;
    private String fullDescription;
    private int cents; // dollar value times 100
    private ItemCategory category;
    //private Image image;

    public Item(String name, Location location) {
        id = ++numberOfItems;
        this.name = name;
        this.location = location;
        timeStamp = new Date();
        creator = Model.getInstance().getLoggedInUser().getUsername();
        shortDescription = "-";
        cents = 0;
        category = null;
    }

    public static int getNumberOfItems() {
        return numberOfItems;
    }

    public static void setNumberOfItems(int numberOfItems) {
        Item.numberOfItems = numberOfItems;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getCents() {
        return cents;
    }

    public void setCents(int cents) {
        this.cents = cents;
    }

    public ItemCategory getCategory() {
        return category;
    }

    public void setCategory(ItemCategory category) {
        this.category = category;
    }
}
