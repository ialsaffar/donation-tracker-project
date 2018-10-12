package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.LocationList;

import java.util.ArrayList;
import java.util.List;

public class LandingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        Button mSignInButton = (Button) findViewById(R.id.sign_out_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Context ctx = getApplicationContext();
        LocationList locations = new LocationList(ctx);
        List<Location> listOfLocations = locations.getLocations();

    }

    private Activity getActivity(){
        return this;
    }
}
