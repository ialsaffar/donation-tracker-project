package com.capybaras.donationtracker.models;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

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
        this(++numberOfItems,
                name,
                new Date("EEE, MMM dd, yyyy, HH:mm:ss z"),
                location.getPhone(),
                Model.getInstance().getLoggedInUser().getUsername(),
                "-",
                null,
                0,
                null);
    }

    public Item(int id,
                String name,
                Date timeStamp,
                String locationNumber,
                String creator,
                String shortDescription,
                String fullDescription,
                int cents,
                ItemCategory category) {
        this.id = id;
        this.name = name;
        this.timeStamp = timeStamp;
        this.creator = creator;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.cents = cents;
        this.category = category;

        LocationList locationList = new LocationList();
        List<Location> listOfLocations = locationList.getLocations();
        for (int i = 0; i < listOfLocations.size(); i++) {
            if (locationNumber.equals(listOfLocations.get(i).getPhone())) {
                this.location = listOfLocations.get(i);
                i = listOfLocations.size();
            }
        }
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

    public void saveAsText (PrintWriter writer) {
        writer.println(id + "\t" + name + "\t" + timeStamp + "\t" + location.getPhone() + "\t" + creator + "\t" + shortDescription + "\t" + fullDescription + "\t" + cents + "\t" + category.getCategoryName());
    }

    public static Item parseEntry(String line) {
        assert line != null;
        String[] tokens = line.split("\t");
        assert tokens.length == 9;

        List<ItemCategory> currentCategories = ItemCategory.getCurrentCategories();
        ItemCategory currentCategory = currentCategories.get(0);
        for (int i = 0; i < currentCategories.size(); i++) {
            if (tokens[8] == currentCategories.get(i).getCategoryName()) {
                currentCategory = currentCategories.get(i);
                i = currentCategories.size();
            }
        }

        Item fromFile = null;

        try {
            fromFile = new Item(parseInt(tokens[0]),
                    tokens[1],
                    new SimpleDateFormat("EEE, MMM dd, yyyy, HH:mm:ss z").parse(tokens[2]),
                    tokens[3],
                    tokens[4],
                    tokens[5],
                    tokens[6],
                    parseInt(tokens[7]),
                    currentCategory);
        } catch (ParseException e){}

        numberOfItems++;

        return fromFile;
    }
}
