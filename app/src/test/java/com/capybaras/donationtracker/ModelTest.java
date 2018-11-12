package com.capybaras.donationtracker;

import org.junit.Before;
import org.junit.Test;

import com.capybaras.donationtracker.models.Model;
import com.capybaras.donationtracker.models.User;
import com.capybaras.donationtracker.models.UserTypes;

import static org.junit.Assert.*;

public class ModelTest {
    private Model model;
    private User user;

    @Before
    public void setUp() {
        model = Model.getInstance();
        user = new User("username", "password", "email", UserTypes.ADMIN);
    }


    @Test
    public void addUserTest() {
        User user1 = user;

        model.addUser(user1);

        assertTrue(model.isUser(user1.getUsername()));


    }

    @Test
    public void getLocationByKeyTest() {

    }



}
