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
    public void addUserParams() {
        //Test for the addUser(username, password, email, userType) works
        //Individually creates each part of a new User
        String username = "user32";
        String password = "pass";
        String email = "email";
        UserTypes userType = UserTypes.ADMIN;

        //Adds the new user through each individual part instead of
        //constructing a new user first
        model.addUser(username, password, email, userType);
        assertTrue(model.isUser(username));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addUserParamsAlreadyExistingUser() {
        //Test for the addUser(username, password, email, userType) works
        //Individually creates each part of a new User
        String username = "userExists";
        String password = "pass";
        String email = "email";
        UserTypes userType = UserTypes.ADMIN;

        //Adds the new user through each individual part instead of
        //constructing a new user first
        model.addUser(username, password, email, userType);

        //Should not add the user because the same username has just
        //been used in the prior line of code (This means that since the
        //username is taken by another user, the current user trying to
        //enter his/her information will be denied and should change
        //his/her username
        model.addUser(username, password, email, userType);
        Assert.fail("Username already exists, should not be added.");

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

}
