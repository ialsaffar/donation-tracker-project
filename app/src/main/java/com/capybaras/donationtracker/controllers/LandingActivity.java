package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.capybaras.donationtracker.models.DataManagementFacade;
import com.capybaras.donationtracker.R;


import java.io.File;

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

        Button mLocationsButton = (Button) findViewById(R.id.locations_button);
        mLocationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LocationListActivity.class);
                startActivity(intent);
            }
        });

        Button mapButton = (Button) findViewById(R.id.mapButton);
        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), MapsActivity.class);
                startActivity(intent);
            }
        });

        Button itemButton = findViewById(R.id.item_button);
        itemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ItemListActivity.class);
                startActivity(intent);
            }
        });

        DataManagementFacade dmf = DataManagementFacade.getInstance();
        File file = new File(this.getFilesDir(), DataManagementFacade.LOCATIONS_FILE_NAME);
        dmf.saveLocationText(file);
    }

    private Activity getActivity(){
        return this;
    }
}
