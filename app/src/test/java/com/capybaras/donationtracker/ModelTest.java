package com.capybaras.donationtracker;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.LocationList;
import com.capybaras.donationtracker.models.Model;
import com.capybaras.donationtracker.models.User;
import com.capybaras.donationtracker.models.UserTypes;

import static org.junit.Assert.*;

public class ModelTest {
    private Model model;

    @Before
    public void setUp() {
        model = Model.getInstance();
    }


    @Test
    public void addUserTest() {
        User user1 = new User("username", "password", "email", UserTypes.ADMIN);
        model.addUser(user1);
        assertTrue(model.isUser(user1.getUsername()));

        User user2 = new User("username2", "password2", "email2", UserTypes.ADMIN);
        assertFalse(model.isUser(user2.getUsername()));

    }

    @Test
    public void addUserTestNull() {
        model.addUser(null);
        Assert.fail("Should have thrown an exception");
    }

    @Test
    public void getLocationByKeyTest() {
        Location location = new Location(101,"name", 11111,
                22222, "Street", "City", "State",
                812823, "Type", "1112223333", "Website");

        LocationList.getLocations().add(location);

        assertEquals(location, model.getLocationByKey(location.getKey()));

    }

    @Test
    public void getLocationByKeyTestException() {
        Location location = new Location(111,"name", 11011,
                22022, "Street1", "City1", "State1",
                815823, "Type", "1112423333", "Website");

        model.getLocationByKey(location.getKey());
        Assert.fail("The previous call should have caused a NoSuchElementException.");

    }



}
