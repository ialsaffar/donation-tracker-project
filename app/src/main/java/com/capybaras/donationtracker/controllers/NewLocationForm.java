package com.capybaras.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capybaras.donationtracker.R;

/**
 * Extra Credit
 * New Location Form Class
 * When tapping on the map, taken to this form to
 * add a new location
 */
public class NewLocationForm extends Activity {

    private EditText nameLocation;
    private EditText website;
    private EditText phoneNumber;
    private Button enterButton;

    private static final String NAME_CODE = "Code for Location Name";
    private static final String WEBSITE_CODE = "Code for Website of Location";
    private static final String PHONE_CODE = "Code for phone number of Location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_form);

        this.nameLocation = findViewById(R.id.nameNewLocation);
        this.website = findViewById(R.id.websiteNewLocation);
        this.phoneNumber = findViewById(R.id.phoneNumberNewLocation);

        this.enterButton = findViewById(R.id.enterButton);

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
    public String getWebsite() {
        return this.website.getText().toString();
    }

    /**
     * Gets the location's phone number from  what was typed in
     * @return the location's phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber.getText().toString();
    }

    /**
     * On click listener for the enter button
     * @param view the view
     */
    public void onEnterPressed(View view) {
        Intent intent = new Intent();
        intent.putExtra(NAME_CODE, this.getLocationName());
        intent.putExtra(WEBSITE_CODE, this.getWebsite());
        intent.putExtra(PHONE_CODE, this.getPhoneNumber());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
