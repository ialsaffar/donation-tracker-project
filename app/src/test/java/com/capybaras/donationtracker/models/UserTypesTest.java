package com.capybaras.donationtracker.models;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTypesTest {

    /*
    Tests main functionality and switch statement
     */
    @Test
    public void test1() {
        UserTypes t1 = UserTypes.getByName("Administrator");
        UserTypes t2 = UserTypes.getByName("Manager");
        UserTypes t3 = UserTypes.getByName("Location Employee");

        assertEquals(t1, UserTypes.ADMIN);
        assertEquals(t2, UserTypes.MANAGER);
        assertEquals(t3, UserTypes.LOCATION_EMPLOYEE);
    }

    /*
    Tests null argument behavior
     */
    @Test(expected = IllegalArgumentException.class)
    public void test2() {
        String str = null;
        UserTypes.getByName(str);
    }

    /*
    Tests unrecognized user type names
     */
    @Test(expected = IllegalArgumentException.class)
    public void test3() {
        UserTypes.getByName("abc");
    }











    private void createUsers() {
        User locUser = new User("Location Joe", "1234", "email@example.com",
                UserTypes.LOCATION_EMPLOYEE);
        User manUser = new User("Manager Jane", "1234", "ejds@df.com",
                UserTypes.MANAGER);
        User admUser = new User("Admin Jack", "222", "dafdas@ds.net",
                UserTypes.ADMIN);

    }

}