package com.capybaras.donationtracker.controller;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.widget.Spinner;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.model.UserTypes;

public class Registration extends AppCompatActivity implements View.OnClickListener {

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

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


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



    @Override
    public void onClick(View view)
    {
        switch (view.getId()) {
            case R.id.registerButton:
                //Check email/username and password are not already used
                Log.d("Button Pressed", "The register button was pressed.");
                //todo would send to account page or login page?
//                setContentView(R.layout.activity_login);
                break;
            case R.id.cancelButton:
                //Go back to Main UI page
                Log.d("Button Pressed", "The cancel button was pressed.");
                //todo would send to login in page or welcome page?
                setContentView(R.layout.activity_login);
                break;
        }
    }

}
