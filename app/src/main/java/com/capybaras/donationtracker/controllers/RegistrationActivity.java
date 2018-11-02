package com.capybaras.donationtracker.controllers;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.support.v7.widget.Toolbar;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Model;
import com.capybaras.donationtracker.models.UserTypes;

public class RegistrationActivity extends AppCompatActivity {

    //UI Widgets
    private Button registerButton;
    private Spinner userTypeSpinner;
    private EditText passwordInputText;
    private EditText reenterPasswordInputText;
    private Button cancelButton;
    private EditText nameTextInputPlainText;
    private EditText emailInputText;
    private LinearLayout linearLayout;
    private Model model;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        this.linearLayout = (LinearLayout) findViewById(R.id.linearLayout);

        this.passwordInputText = (EditText) findViewById(R.id.passwordInputText);
        this.reenterPasswordInputText = (EditText) findViewById(R.id.reenterPasswordInputText);
        this.nameTextInputPlainText = (EditText) findViewById(R.id.nameTextInputPlainText);
        this.emailInputText = (EditText) findViewById(R.id.emailInputText);

        this.registerButton = (Button) findViewById(R.id.registerButton);
        this.cancelButton = (Button) findViewById(R.id.cancelButton);

        this.userTypeSpinner = (Spinner) findViewById(R.id.userTypeSpinner);

        //Sets the adapter to display the user types
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, UserTypes.values());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.userTypeSpinner.setAdapter(adapter);
        model = Model.getInstance();
    }


    /**
     * Event handler for the Register button
     *
     * @param view
     */
    public void onRegisterPressed(View view) {
        if (this.allBoxesFilled()) {
            if (this.passwordsMatch()) {
                //means password and re entered passwords are equal (may continue)
                model.addUser(this.nameTextInputPlainText.getText().toString(), this.passwordInputText.getText().toString());
                finish();
            } else {
                Snackbar.make(view, "Your passwords do not match. Please try again.", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        } else {
            Snackbar.make(view, "Not all boxes are filled.", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
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
        finish();
    }

    private boolean allBoxesFilled() {
       return !(this.nameTextInputPlainText.getText().toString().isEmpty()
       || this.emailInputText.getText().toString().isEmpty()
       || this.passwordInputText.getText().toString().isEmpty()
       || this.reenterPasswordInputText.getText().toString().isEmpty());
    }

    private boolean passwordsMatch() {
        return this.passwordInputText.getText().toString().equals(this.reenterPasswordInputText.getText().toString());
    }

    private boolean emailAlreadyUse() {
        //Cannot make this method yet
        //(No where to store the data)
        return false;
    }

}