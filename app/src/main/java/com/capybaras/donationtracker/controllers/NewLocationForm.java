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

    private EditText nameLocation;
    private EditText type;
    private EditText phoneNumber;

    public static final String NAME_CODE = "Code for Location Name";
    public static final String TYPE_CODE = "Code for Website of Location";
    public static final String PHONE_CODE = "Code for phone number of Location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_form);

        this.nameLocation = findViewById(R.id.nameNewLocation);
        this.type = findViewById(R.id.typeNewLocation);
        this.phoneNumber = findViewById(R.id.phoneNumberNewLocation);
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
     * On click listener for the enter button
     * @param view the view
     */
    @SuppressWarnings("unused")
    public void onEnterPressed(View view) {
        Intent intent = new Intent();
        intent.putExtra(NAME_CODE, this.getLocationName());
        intent.putExtra(TYPE_CODE, this.getType());
        intent.putExtra(PHONE_CODE, this.getPhoneNumber());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
