package com.capybaras.donationtracker;

import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.ItemCategory;
import com.capybaras.donationtracker.models.LocationList;
import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.Model;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static com.capybaras.donationtracker.models.LocationList.getLocations;
import static org.junit.Assert.*;

/**
 * Created by sjes3
 */

public class ItemTest {
    private Location location;
    private Item item;
    private Date date;
    private ItemCategory itemCategory;

    @Before
    public void setup() {
        new LocationList();
        Model.getInstance();
        date = new Date();
        itemCategory = ItemCategory.getCurrentCategories().get(3);
        if (getLocations() != null) {
            location = getLocations().get(0);
        } else {
            location = new Location (1,
                                     "AFD Station 4",
                                     33.75416,
                                     -84.37742,
                                     "309 EDGEWOOD AVE SE",
                                     "Atlanta",
                                     "GA",
                                     30332,
                                     "Drop Off",
                                     "(404) 555 - 3456",
                                     "www.afd04.atl.ga");
        }
        item = new Item(45,
                        "Lamp",
                        date,
                        location,
                        "Steve",
                        "It's a lamp.",
                        "It's a lamp. What else do you need to know?",
                        1099,
                        itemCategory);
    }

    @Test
    public void getItemNumberTest() {
        assertEquals(1, Item.getNumberOfItems());
    }

    @Test
    public void setItemNumberTest() {
        Item.setNumberOfItems(3);
        assertEquals(3, Item.getNumberOfItems());
    }

    @Test
    public void getLongDescriptionTest() {
        assertEquals("It's a lamp. What else do you need to know?", item.getFullDescription());
    }

    @Test
    public void setLongDescriptionTest() {
        item.setFullDescription("Look at me! I'm a lamp!");
        assertEquals("Look at me! I'm a lamp!", item.getFullDescription());
    }

    @Test
    public void getShortDescriptionTest() {
        assertEquals("It's a lamp.", item.getFullDescription());
    }

    @Test
    public void setShortDescriptionTest() {
        item.setShortDescription("Just an ordinary lamp.");
        assertEquals("Just an ordinary lamp.", item.getFullDescription());
    }

    @Test
    public void getIdTest() {
        assertEquals(45, item.getId());
    }

    @Test
    public void setIdTest() {
        item.setId(54);
        assertEquals(54, item.getId());
    }

    @Test
    public void getNameTest() {
        assertEquals("Lamp", item.getName());
    }

    @Test
    public void setNameTest() {
        item.setName("Blue lamp");
        assertEquals("Blue lamp", item.getName());
    }

    @Test
    public void getTimeStampTest() {
        assertEquals(date, item.getTimeStamp());
    }

    @Test
    public void setTimeStampTest() {
        Date newDate = new Date();
        item.setTimeStamp(newDate);
        assertEquals(newDate, item.getTimeStamp());
    }

    @Test
    public void getLocationTest() {
        assertEquals(location, item.getLocation());
    }

    @Test
    public void setLocationTest() {
        Location newLocation;
        if (getLocations() != null) {
            newLocation = getLocations().get(0);
        } else {
            newLocation = new Location (
                    2,
                    "BOYS & GILRS CLUB W.W. WOOLFOLK",
                    33.73182,
                    -84.43971,
                    "1642 RICHLAND RD",
                    "Atlanta",
                    "GA",
                    30332,
                    "Store",
                    "(404) 555 - 1234",
                    "www.bgc.wool.ga");
        }
        item.setLocation(newLocation);
        assertEquals(newLocation, item.getLocation());
    }

    @Test
    public void getCreatorTest() {
        assertEquals("Steve", item.getCreator());
    }

    @Test
    public void setCreatorTest() {
        item.setCreator("Steven");
        assertEquals("Steven", item.getCreator());
    }

    @Test
    public void getCentsTest() {
        assertEquals(1099, item.getCents());
    }

    @Test
    public void setCentsTest() {
        item.setCents(999);
        assertEquals(999, item.getCents());
    }

    @Test
    public void getCategoryTest() {
        assertEquals(ItemCategory.getCurrentCategories().get(3), item.getCategory());
    }

    @Test
    public void setCategoryTest() {
        item.setCategory(ItemCategory.getCurrentCategories().get(4));
        assertEquals(ItemCategory.getCurrentCategories().get(4), item.getCategory());
    }

    @Test
    public void parseEntryTest() {
        String dateFormatted = "" + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + ", " + date.getHours() + ":" + date.getMinutes();
        String line = item.getNumberOfItems() + "\t" + item.getId() + "\t" + item.getName() + "\t" + dateFormatted + "\t" + item.getCreator() + "\t" + item.getShortDescription() + "\t" + item.getFullDescription() + "\t" + item.getCents() + "\t" + item.getCategory().getCategoryName();
        String locLine = location.getKey() + "\t" + location.getName() + "\t" + location.getLatitude() + "\t" + location.getLongitude() + "\t" + location.getStreetAddress() + "\t" + location.getCity() + "\t" + location.getState() + "\t" + location.getZipCode() + "\t" + location.getType() + "\t" + location.getPhone() + "\t" + location.getWebsite();
        assertEquals(item, Item.parseEntry(line, locLine));
    }

    @Test
    public void parseEntryWrongStringTest() {
        String dateFormatted = "" + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + ", " + date.getHours() + ":" + date.getMinutes();
        String line = item.getNumberOfItems() + "\t" + item.getName() + "\t" + dateFormatted + "\t" + item.getCreator() + "\t" + item.getShortDescription() + "\t" + item.getFullDescription() + "\t" + item.getCents() + "\t" + item.getCategory().getCategoryName();
        String locLine = location.getKey() + "\t" + location.getName() + "\t" + location.getLatitude() + "\t" + location.getLongitude() + "\t" + location.getStreetAddress() + "\t" + location.getCity() + "\t" + location.getState() + "\t" + location.getZipCode() + "\t" + location.getType() + "\t" + location.getPhone() + "\t" + location.getWebsite();
        assertEquals(item, Item.parseEntry(line, locLine));
    }

    @Test
    public void parseEntryNewLocationTest() {
        String dateFormatted = "" + date.getMonth() + "/" + date.getDay() + "/" + date.getYear() + ", " + date.getHours() + ":" + date.getMinutes();
        String line = item.getNumberOfItems() + "\t" + item.getName() + "\t" + dateFormatted + "\t" + item.getCreator() + "\t" + item.getShortDescription() + "\t" + item.getFullDescription() + "\t" + item.getCents() + "\t" + item.getCategory().getCategoryName();
        String locLine = 2 + "\t" + "House" + "\t" + 34.3434 + "\t" + -4.3475 + "\t" + "Location" + "\t" + "Atlanta" + "\t" + "Georgia" + "\t" + 74893 + "\t" + "Drop-off" + "\t" + "Number" + "\t" + "www.com";
        Location newLocation = new Location(2,
                "House",
                34.3434,
                -4.3475,
                "Location",
                "Atlanta",
                "Georgia",
                74893,
                "Drop-off",
                "Number",
                "www.com");
        Item newItem = Item.parseEntry(line, locLine);
        assertEquals(item, newItem);
        assertEquals(item.getLocation(), newLocation);
        boolean inLocList = false;
        List<Location> locations = LocationList.getLocations();
        for (int i = 0; i < locations.size(); i++) {
            if (newItem.getLocation() == locations.get(i)) {
                inLocList = true;
            }
        }
        assertTrue(inLocList);
    }

    @Test
    public void twoArgConstructorTest() {
        Item item2arg = new Item ("Table",
                location);
        assertFalse(item2arg == null);
        assertFalse(item2arg.getName() == null);
    }
}
