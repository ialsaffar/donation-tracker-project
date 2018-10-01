package com.capybaras.donationtracker.controller;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;
import android.widget.Toast;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.model.UserTypes;

public class Registration extends AppCompatActivity {

    //UI Widgets
    private Button registerButton;
    private Spinner userTypeSpinner;
    private EditText passwordInputText;
    private EditText reenterPasswordInputText;
    private Button cancelButton;
    private EditText nameTextInputPlainText;
    private EditText emailInputText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.passwordInputText = (EditText) findViewById(R.id.passwordInputText);
        this.reenterPasswordInputText = (EditText) findViewById(R.id.reenterPasswordInputText);
        this.nameTextInputPlainText = (EditText) findViewById(R.id.nameTextInputPlainText);
        this.emailInputText = (EditText) findViewById(R.id.emailInputText);

        this.registerButton = (Button) findViewById(R.id.registerButton);
        this.cancelButton = (Button) findViewById(R.id.cancelButton);

        this.userTypeSpinner = (Spinner) findViewById(R.id.userTypeSpinner);

        //Sets the adapter to display the user types
        ArrayAdapter<UserTypes> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, UserTypes.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.userTypeSpinner.setAdapter(adapter);

    }


    /**
     * Event handler for the Register button
     *
     * @param view
     */
    public void onRegisterPressed(View view) {
        Log.d("Button Pressed", "The register button was pressed.");

        if (this.passwordInputText.equals(this.reenterPasswordInputText)) {
            //means password and re entered passwords are equal (may continue)
            //todo add to the database of user info

            setContentView(R.layout.content_main);
            finish();

        } else {
            Toast.makeText(Registration.this, "The passwords do not match. Please try again.", Toast.LENGTH_LONG);
            finish();
        }
    }

    /**
     * Event handler for the Cancel Button
     *
     * Nothing is saved
     * @param view
     */
    public void onCancelPressed(View view) {
        Log.d("Donation Tracker App", "Cancel Button Pressed");
        setContentView(R.layout.activity_login);
        finish();
    }

}
