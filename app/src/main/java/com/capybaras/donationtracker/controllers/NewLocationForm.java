package com.capybaras.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;

import com.capybaras.donationtracker.R;

/**
 * Extra Credit
 * New Location Form Class
 * When tapping on the map, taken to this form to
 * add a new location
 */
public class NewLocationForm extends Activity {

    private EditText key;
    private EditText nameLocation;
    private EditText streetAddress;
    private EditText city;
    private EditText state;
    private EditText zipCode;
    private EditText type;
    private EditText phoneNumber;
    private EditText website;

    public static final String KEY_CODE = "Code for Location Key";
    public static final String NAME_CODE = "Code for Location Name";
    public static final String ADDRESS_CODE = "Code for Location's street address";
    public static final String CITY_CODE = "Code for Location's city";
    public static final String STATE_CODE = "Code for Location's state";
    public static final String ZIPCODE_CODE = "Code for Location's zip code";
    public static final String TYPE_CODE = "Code for Website of Location";
    public static final String PHONE_CODE = "Code for phone number of Location";
    public static final String WEBSITE_CODE = "Code for Location's website";

    private static final String DEFAULT_KEY = "101";
    private static final String DEFAULT_NAME = "Unnamed Location";
    private static final String DEFAULT_ADDRESS = "Unspecified Address";
    private static final String DEFAULT_CITY = "Unnamed City";
    private static final String DEFAULT_STATE = "Unnamed State";
    private static final String DEFAULT_ZIPCODE = "12345";
    private static final String DEFAULT_TYPE = "Unspecified Type";
    private static final String DEFAULT_PHONE = "1 800 123 456 7890";
    private static final String DEFAULT_WEBSITE = "www.nothing.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_form);

        this.key = findViewById(R.id.keyNewLocation);
        this.nameLocation = findViewById(R.id.nameNewLocation);
        this.streetAddress = findViewById(R.id.streetAddressNewLocation);
        this.city = findViewById(R.id.cityNewLocation);
        this.state = findViewById(R.id.stateNewLocation);
        this.zipCode = findViewById(R.id.zipCodeNewLocation);
        this.type = findViewById(R.id.typeNewLocation);
        this.phoneNumber = findViewById(R.id.phoneNumberNewLocation);
        this.website = findViewById(R.id.websiteNewLocation);
    }

    /**
     * Gets the location's name from what was typed in
     * @return the Location's name
     */
    public String getLocationName() {
        return this.nameLocation.getText().toString();
    }

    /**
     * Gets the location's website from what was typed in
     * @return the location's website
     */
    public String getType() {
        return this.type.getText().toString();
    }

    /**
     * Gets the location's phone number from  what was typed in
     * @return the location's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber.getText().toString();
    }

    /**
     * Gets the location's key from what was typed in
     * @return the key
     */
    public String getKey() { return this.key.getText().toString();
    }

    /**
     * Gets the location's street address from what was typed in
     * @return the street address
     */
    public String getStreetAddress() {
        return this.streetAddress.getText().toString();
    }

    /**
     * Gets the location's city from what was typed in
     * @return the city
     */
    public String getCity() {
        return this.city.getText().toString();
    }

    /**
     * Gets the location's state from what was typed in
     * @return the state
     */
    public String getState() {
        return this.state.getText().toString();
    }

    /**
     * Gets the location's zip code from what was typed in
     * @return the zip code
     */
    public String getZipCode() {
        return this.zipCode.getText().toString();
    }

    /**
     * Gets the location's zip code from what was typed in
     * @return the website
     */
    public String getWebsite() {
        return this.website.getText().toString();
    }

    /**
     * On click listener for the enter button
     * @param view the view
     */
    public void onEnterPressed(View view) {
        Intent intent = new Intent();

        //Checking for Key Entry
        if (this.getKey().isEmpty()) {
            intent.putExtra(KEY_CODE, DEFAULT_KEY);
        } else {
            intent.putExtra(KEY_CODE, this.getKey());
        }
        //Checking for Name Entry
        if (this.getLocationName().isEmpty()) {
            intent.putExtra(NAME_CODE, DEFAULT_NAME);
        } else {
            intent.putExtra(NAME_CODE, this.getLocationName());
        }
        //Checking for Address Entry
        if (this.getStreetAddress().isEmpty()) {
            intent.putExtra(ADDRESS_CODE, DEFAULT_ADDRESS);
        } else {
            intent.putExtra(ADDRESS_CODE, this.getStreetAddress());
        }
        //Checking for City Entry
        if (this.getCity().isEmpty()) {
            intent.putExtra(CITY_CODE, DEFAULT_CITY);
        } else {
            intent.putExtra(CITY_CODE, this.getCity());
        }
        //Checking for State
        if (this.getState().isEmpty()) {
            intent.putExtra(STATE_CODE, DEFAULT_STATE);
        } else {
            intent.putExtra(STATE_CODE, this.getState());
        }
        //Checking for ZipCode Entry
        if (this.getZipCode().isEmpty()) {
            intent.putExtra(ZIPCODE_CODE, DEFAULT_ZIPCODE);
        } else {
            intent.putExtra(ZIPCODE_CODE, this.getZipCode());
        }
        //Checking for type Entry
        if (this.getType().isEmpty()) {
            intent.putExtra(TYPE_CODE, DEFAULT_TYPE);
        } else {
            intent.putExtra(TYPE_CODE, this.getType());
        }
        //Checking for Phone Entry
        if (this.getPhoneNumber().isEmpty()) {
            intent.putExtra(PHONE_CODE, DEFAULT_PHONE);
        } else {
            intent.putExtra(PHONE_CODE, this.getPhoneNumber());
        }
        //Checking for WebsiteEntry
        if (this.getWebsite().isEmpty()) {
            intent.putExtra(WEBSITE_CODE, DEFAULT_WEBSITE);
        } else {
            intent.putExtra(WEBSITE_CODE, this.getWebsite());
        }

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
