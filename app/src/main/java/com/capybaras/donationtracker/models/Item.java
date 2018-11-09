package com.capybaras.donationtracker.models;

import java.io.PrintWriter;
import java.text.ParseException;
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
                new Date(),
                location,
                Model.getInstance().getLoggedInUser().getUsername(),
                "-",
                null,
                0,
                null);
    }

    public Item(int id,
                String name,
                Date timeStamp,
                Location location,
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
        this.location = location;
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
        String dateFormatted = "" + timeStamp.getMonth() + "/" + timeStamp.getDay() + "/" + timeStamp.getYear() + ", " + timeStamp.getHours() + ":" + timeStamp.getMinutes();
        writer.println(id + "\t" + name + "\t" + dateFormatted + "\t" + creator + "\t" + shortDescription + "\t" + fullDescription + "\t" + cents + "\t" + category.getCategoryName());
        location.saveAsTextSansItems(writer);
    }

    public static Item parseEntry(String line, String locLine) {
        assert line != null;
        String[] tokens = line.split("\t");
        ItemCategory currentCategory = ItemCategory.getCurrentCategories().get(0);
        if (tokens.length == 8) {
            List<ItemCategory> currentCategories = ItemCategory.getCurrentCategories();
            currentCategory = currentCategories.get(0);
            for (int i = 0; i < currentCategories.size(); i++) {
                if (tokens[7] == currentCategories.get(i).getCategoryName()) {
                    currentCategory = currentCategories.get(i);
                    i = currentCategories.size();
                }
            }
        }

        Item fromFile = null;

        Location loc = Location.parseEntry(locLine);
        List<Location> locList = LocationList.getLocations();
        for (int i = 0; i < locList.size(); i++) {
            if ((loc.getPhone().equals(locList.get(i).getPhone())) && (loc.getName().equals(locList.get(i).getName()))) {
                loc = locList.get(i);
                i = locList.size();
            }
        }

        try {
            fromFile = new Item(parseInt(tokens[0]),
                    tokens[1],
                    new SimpleDateFormat("MM/dd/yyyy, hh:mm").parse(tokens[2]),
                    loc,
                    tokens[3],
                    tokens[4],
                    tokens[5],
                    parseInt(tokens[6]),
                    currentCategory);
        } catch (ParseException e){}

        numberOfItems++;

        return fromFile;
    }
}
