package com.capybaras.donationtracker;

import android.support.test.runner.AndroidJUnit4;

import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.LocationList;
import com.capybaras.donationtracker.models.Model;
import com.capybaras.donationtracker.models.User;
import com.capybaras.donationtracker.models.UserTypes;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * Model test: will execute on an Android device.
 */
@RunWith(AndroidJUnit4.class)
public class ModelTest {
    private Model model;

    @Before
    public void setUp() {
        model = Model.getInstance();
    }


    @Test
    public void addUserTest() {
        //method addUser(User user)
        //Tests to make sure a valid user can be passed in
        User user1 = new User("username", "password", "email", UserTypes.ADMIN);
        model.addUser(user1);
        //Checks to make sure the user has been entered into the system
        assertTrue(model.isUser(user1.getUsername()));

        //A new user
        User user2 = new User("username2", "password2", "email2", UserTypes.ADMIN);
        //The user2 was never added to the model
        //This checks to make sure the username is not somehow added
        assertFalse(model.isUser(user2.getUsername()));

    }

    @Test (expected = IllegalArgumentException.class)
    public void addUserTestNull() {
        //Checks to make sure that the call to addUser will
        //fail if a null value is passed in (there is no such thing as a null user)

        model.addUser(null);
        Assert.fail("Should have thrown an exception");
    }

    @Test
    public void addUserParams() {
        //Test for the addUser(username, password, email, userType) works
        //Individually creates each part of a new User
        String username = "username";
        String password = "pass";
        String email = "email";
        UserTypes userType = UserTypes.ADMIN;

        //Adds the new user through each individual part instead of
        //constructing a new user first
        model.addUser(username, password, email, userType);
        assertTrue(model.isUser(username));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addUserParamsUsernameNull() {
        //Individual parts to a User (except username)
        String password = "pass";
        String email = "email";
        UserTypes userType = UserTypes.ADMIN;

        //Adds a new "user" though with a null username
        //This should fail and should not execute the method any further
        model.addUser(null, password, email, userType);
        Assert.fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void addUserParamsPasswordNull() {
        //Individual parts to a User (except password)
        String username = "user";
        String email = "email";
        UserTypes userType = UserTypes.ADMIN;

        //Adds a new "user" though with a null password
        //This should fail and should not execute the method any further
        model.addUser(username, null, email, userType);
        Assert.fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void addUserParamsEmailNull() {
        //Individual parts to a User (except email)
        String username = "user";
        String password = "pass";
        UserTypes userType = UserTypes.ADMIN;

        //Adds a new "user" though with a null email
        //This should fail and should not execute the method any further
        model.addUser(username, password, null, userType);
        Assert.fail();
    }

    @Test (expected = IllegalArgumentException.class)
    public void addUserParamsUserTypeNull() {
        //Individual parts to a User (except userType)
        String username = "user";
        String password = "pass";
        String email = "email";

        //Adds a new "user" though with a null userType
        //This should fail and should not execute the method any further
        model.addUser(username, password, email, null);
        Assert.fail();
    }


    @Test
    public void getLocationByKeyTest() {
        //Creates a new Location
        Location location = new Location(101,"name", 11111,
                22222, "Street", "City", "State",
                812823, "Type", "1112223333", "Website");

        //Adds the new location to the location list
        LocationList.getLocations().add(location);

        //Checks to make sure the newly added location is found by its unique key
        assertEquals(location, model.getLocationByKey(location.getKey()));

    }

    @Test (expected = NoSuchElementException.class)
    public void getLocationByKeyTestException() {
        //Creates a new Location
        Location location = new Location(111,"name", 11011,
                22022, "Street1", "City1", "State1",
                815823, "Type", "1112423333", "Website");

        //The new location is not added to the location list, and therefore should not be
        //found when searched by its unique key

        //Note: It is not possible to skip the for loop, since no matter the key
        //the method will always iterate through the entire Location list despite
        //whether the location exists or not
        model.getLocationByKey(location.getKey());
        Assert.fail("The previous call should have caused a NoSuchElementException.");

    }
}
