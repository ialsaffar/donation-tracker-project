package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.DataManagementFacade;
import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.LocationList;
import com.capybaras.donationtracker.models.Model;
import com.capybaras.donationtracker.models.User;

import java.io.File;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    File getFilesDir = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSignInButton = (Button) findViewById(R.id.sign_in_button);
        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), RegistrationActivity.class);
                startActivity(intent);
            }
        });

        DataManagementFacade dmf = DataManagementFacade.getInstance();
        File file;

        file = new File(this.getFilesDir(), DataManagementFacade.ITEMS_FILE_NAME);
        dmf.loadItemText(file);
        file = new File(this.getFilesDir(), DataManagementFacade.USERS_FILE_NAME);
        dmf.loadUserText(file);
//        file = new File(this.getFilesDir(), DataManagementFacade.LOCATIONS_FILE_NAME);
//        dmf.loadLocationText(file);
        getFilesDir = getFilesDir().getAbsoluteFile();

        List<Item> items = Location.getItems();
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).getName());
            System.out.println("User Location: " + items.get(i).getLocation());
        }
        List<Location> locs = LocationList.getLocations();
        for (int j = 0; j < locs.size(); j++) {
            System.out.println(locs.get(j));
        }
    }

    private Activity getActivity(){
        return this;
    }

    public  File getFileDirectory() {return getFilesDir; }
}
