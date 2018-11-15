package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.DataManagementFacade;

import java.io.File;

/**
 * MainActivity class
 */
public class MainActivity extends AppCompatActivity {

    private File getFilesDir = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button mSignInButton = findViewById(R.id.sign_in_button);
        Button mRegisterButton = findViewById(R.id.register_button);
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
    }

    private Activity getActivity(){
        return this;
    }

    /**
     * Gets the file Directory
     * @return the File
     */
    public  File getFileDirectory() {return getFilesDir; }
}
