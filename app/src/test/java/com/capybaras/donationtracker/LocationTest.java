package com.capybaras.donationtracker;

import com.capybaras.donationtracker.models.Location;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by mogedi on 11/13/2018.
 */

public class LocationTest {
    private Location location1;
    private Location location2;

    @Before
    public void setUp() {
        location1 = new Location(
                1,
                "AFD Station 4",
                33.75416,
                -84.37742,
                "309 EDGEWOOD AVE SE",
                "Atlanta",
                "GA",
                30332,
                "Drop Off",
                "(404) 555 - 3456",
                "www.afd04.atl.ga"
        );

        location2 = new Location(
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
                "www.bgc.wool.ga"
        );
    }

    @Test
    public void locationParseEntryTest() {
        assertEquals("Location1 Parse Test",
                location1,
                location1.parseEntry("1\tAFD Station 4\t33.75416\t-84.37742\t309 EDGEWOOD AVE SE\tAtlanta\tGA\t30332\tDrop Off\t(404) 555 - 3456\twww.afd04.atl.ga"));
        assertEquals("Location2 Parse Test",
                location2,
                location2.parseEntry("2\tBOYS & GILRS CLUB W.W. WOOLFOLK\t33.73182\t-84.43971\t1642 RICHLAND RD\tAtlanta\tGA\t30332\tStore\t(404) 555 - 1234\twww.bgc.wool.ga"));
    }

    @Test
    public void locationKeyAccessorTest() {
        assertEquals("Location1 Get Key",1, location1.getKey());

        assertEquals("Location2 Get Key",2, location2.getKey());

        location1.setKey(3);
        location2.setKey(4);

        assertEquals("Location1 Set Key",3, location1.getKey());

        assertEquals("Location2 Set Key",4, location2.getKey());
    }



    @Test
    public void locationNameAccessorTest() {
        assertEquals("Location1 Get Name","AFD Station 4", location1.getName());
        assertEquals("Location2 Get Name","BOYS & GILRS CLUB W.W. WOOLFOLK" , location2.getName());

        location1.setName("name1");
        location2.setName("name2");

        assertEquals("Location1 Set Name","name1", location1.getName());
        assertEquals("Location2 Set Name","name2", location2.getName());
    }

    @Test
    public void locationLatitudeAccessorTest() {
        assertEquals("Location1 Get Latitude",33.75416, location1.getLatitude(), 0.0);
        assertEquals("Location2 Get Latitude",33.73182 , location2.getLatitude(), 0.0);

        location1.setLatitude(25.00);
        location2.setLatitude(50.00);

        assertEquals("Location1 Set Latitude", 25.00, location1.getLatitude(), 0.0);
        assertEquals("Location2 Set Latitude", 50.00, location2.getLatitude(), 0.0);
    }

    @Test
    public void locationLongitudeAccessorTest() {
        assertEquals("Location1 Get Longitude",-84.37742, location1.getLongitude(), 0.0);
        assertEquals("Location2 Get Longitude",-84.43971 , location2.getLongitude(), 0.0);

        location1.setLongitude(25.00);
        location2.setLongitude(50.00);

        assertEquals("Location1 Set Longitude", 25.00, location1.getLongitude(), 0.0);
        assertEquals("Location2 Set Longitude", 50.00, location2.getLongitude(), 0.0);
    }

    @Test
    public void locationStreetAddressAccessorTest() {
        assertEquals("Location1 Get Street Address","309 EDGEWOOD AVE SE", location1.getStreetAddress());
        assertEquals("Location2 Get Street Address","1642 RICHLAND RD" , location2.getStreetAddress());

        location1.setStreetAddress("streetAddress1");
        location2.setStreetAddress("streetAddress2");

        assertEquals("Location1 Set Street Address","streetAddress1", location1.getStreetAddress());
        assertEquals("Location2 Set Street Address","streetAddress2", location2.getStreetAddress());
    }

    @Test
    public void locationCityAccessorTest() {
        assertEquals("Location1 Get City","Atlanta", location1.getCity());
        assertEquals("Location2 Get City","Atlanta" , location2.getCity());

        location1.setCity("New York");
        location2.setCity("Dallas");

        assertEquals("Location1 Set City","New York", location1.getCity());
        assertEquals("Location2 Set City","Dallas", location2.getCity());
    }

    @Test
    public void locationStateAccessorTest() {
        assertEquals("Location1 Get State","GA", location1.getState());
        assertEquals("Location2 Get State","GA" , location2.getState());

        location1.setState("NY");
        location2.setState("TX");

        assertEquals("Location1 Set State","NY", location1.getState());
        assertEquals("Location2 Set State","TX", location2.getState());
    }

    @Test
    public void locationZipCodeAccessorTest() {
        assertEquals("Location1 Get Zip Code",30332, location1.getZipCode());
        assertEquals("Location2 Get Zip Code",30332 , location2.getZipCode());

        location1.setZipCode(01234);
        location2.setZipCode(56789);

        assertEquals("Location1 Set Zip Code",01234, location1.getZipCode());
        assertEquals("Location2 Set Zip Code",56789, location2.getZipCode());
    }

    @Test
    public void locationTypeAccessorTest() {
        assertEquals("Location1 Get Type","Drop Off", location1.getType());
        assertEquals("Location2 Get Type","Store", location2.getType());

        location1.setType("Store");
        location2.setType("Drop Off");

        assertEquals("Location1 Set Type","Store", location1.getType());
        assertEquals("Location2 Set Type","Drop Off", location2.getType());
    }

    @Test
    public void locationPhoneAccessorTest() {
        assertEquals("Location1 Get Phone","(404) 555 - 3456", location1.getPhone());
        assertEquals("Location2 Get Phone","(404) 555 - 1234", location2.getPhone());

        location1.setPhone("(000) 111 2345");
        location2.setPhone("(999) 111 6789");

        assertEquals("Location1 Set Phone","(000) 111 2345", location1.getPhone());
        assertEquals("Location2 Set Phone","(999) 111 6789", location2.getPhone());
    }

    @Test
    public void locationWebsiteAccessorTest() {
        assertEquals("Location1 Get Website","www.afd04.atl.ga", location1.getWebsite());
        assertEquals("Location2 Get Website","www.bgc.wool.ga", location2.getWebsite());

        location1.setWebsite("www.nothing.com");
        location2.setWebsite("www.google.com");

        assertEquals("Location1 Set State","www.nothing.com", location1.getWebsite());
        assertEquals("Location2 Set State","www.google.com", location2.getWebsite());
    }
}