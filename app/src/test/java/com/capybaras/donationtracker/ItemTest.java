package com.capybaras.donationtracker;

import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.LocationList;
import com.capybaras.donationtracker.models.Location;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

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
        date = new Date();
        itemCategory = ItemCategory.getCurrentCategories.get(0);
        if (LocationList.getLocations != null) {
            location = LocationList.getLocations.get(0);
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
    public void 

    @Test
    public void twoArgConstructorTest() {
        Item item2arg = new Item ("Table",
                                  location);
        assertFalse(item2arg == null);
        assertFalse(item2arg.getName() == null);
    }
}
