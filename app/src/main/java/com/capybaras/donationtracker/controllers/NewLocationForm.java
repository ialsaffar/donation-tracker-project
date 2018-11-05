package com.capybaras.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.capybaras.donationtracker.R;

public class NewLocationForm extends Activity {

    private EditText nameLocation;
    private EditText website;
    private EditText phoneNumber;
    private Button enterButton;

    public static final String NAME_CODE = "Code for Location Name";
    public static final String WEBSITE_CODE = "Code for Website of Location";
    public static final String PHONE_CODE = "Code for phone number of Location";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_location_form);

        this.nameLocation = (EditText) findViewById(R.id.nameNewLocation);
        this.website = (EditText) findViewById(R.id.websiteNewLocation);
        this.phoneNumber = (EditText) findViewById(R.id.phoneNumberNewLocation);

        this.enterButton = (Button) findViewById(R.id.enterButton);

    }

    public String getLocationName() {
        return this.nameLocation.getText().toString();
    }

    public String getWebsite() {
        return this.website.getText().toString();
    }

    public String getPhoneNumber() {
        return this.phoneNumber.getText().toString();
    }

    public void onEnterPressed(View view) {
        Intent intent = new Intent();
        intent.putExtra(NAME_CODE, this.getLocationName());
        intent.putExtra(WEBSITE_CODE, this.getWebsite());
        intent.putExtra(PHONE_CODE, this.getPhoneNumber());

        setResult(Activity.RESULT_OK, intent);
        finish();
    }

}
