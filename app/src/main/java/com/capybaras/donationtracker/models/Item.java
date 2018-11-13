package com.capybaras.donationtracker.models;

import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static java.lang.Integer.parseInt;

/**
 * Item class: Holds all information
 * specific to an item
 */
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

    /**
     * Default Constructor for an Item
     * @param name the name of the item
     * @param location the location of the item
     */
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

    /**
     * Another constructor for the Item class
     * @param id the id of the item
     * @param name the name of the item
     * @param timeStamp the time stamp of the item
     * @param location the location of the item
     * @param creator the creator of the item
     * @param shortDescription a brief description
     * @param fullDescription a detailed description
     * @param cents cost in cents of the item
     * @param category category of item
     */
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

    /**
     * Gets the total number of items
     * @return the number of items
     */
    public static int getNumberOfItems() {
        return numberOfItems;
    }

    /**
     * Sets the number of items
     * @param numberOfItems sets a new number of items
     */
    public static void setNumberOfItems(int numberOfItems) {
        Item.numberOfItems = numberOfItems;
    }

    /**
     * Gets the full description of an item
     * @return the description
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Sets the full description of an item
     * @param fullDescription full description of item
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * Gets the id of an item
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the ID of the item
     * @param id the new id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets the name of the item
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the item
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the time stamp of the item
     * @return the date
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the time stamp of the item
     * @param timeStamp the date
     */
    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the location of the item
     * @return the location
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location of the item
     * @param location the location of the item
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the creator/maker of the item
     * @return the name of the creator
     */
    public String getCreator() {
        return creator;
    }

    /**
     * Sets the creator of the item
     * @param creator the creator of the item
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * Gets the short description of the item
     * @return the description
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the short description of the item
     * @param shortDescription the new short description
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets the price in cents of the item
     * @return the price in cents
     */
    public int getCents() {
        return cents;
    }

    /**
     * Sets the price of the item in cents
     * @param cents the price in cents
     */
    public void setCents(int cents) {
        this.cents = cents;
    }

    /**
     * Gets the item's category
     * @return the item's category
     */
    public ItemCategory getCategory() {
        return category;
    }

    /**
     * Sets the category of the item
     * @param category the new category
     */
    public void setCategory(ItemCategory category) {
        this.category = category;
    }

    /**
     * Saves the item's information as a text file
     * @param writer the print writer
     */
    public void saveAsText (PrintWriter writer) {
        String dateFormatted = "" + timeStamp.getMonth() + "/" + timeStamp.getDay() + "/" + timeStamp.getYear() + ", " + timeStamp.getHours() + ":" + timeStamp.getMinutes();
        writer.println(id + "\t" + name + "\t" + dateFormatted + "\t" + creator + "\t" + shortDescription + "\t" + fullDescription + "\t" + cents + "\t" + category.getCategoryName());
        location.saveAsTextSansItems(writer);
    }

    /**
     * Parses through the item's entry
     * @param line the line in the file
     * @param locLine location of line
     * @return the Item
     */
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
        } catch (ParseException e){
            //TODO add a better catch block
            e.getMessage();
        }

        numberOfItems++;

        return fromFile;
    }
}
