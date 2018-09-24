package com.capybaras.donationtracker.controller;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.capybaras.donationtracker.R;
import static android.Manifest.permission.READ_CONTACTS;

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });





    }







}
