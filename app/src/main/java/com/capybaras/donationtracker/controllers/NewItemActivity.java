package com.capybaras.donationtracker.controllers;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.ItemCategory;
import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.Model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewItemActivity extends Activity {

    // GUI Objects:
    private Button cancelButton;
    private Button submitButton;
    private EditText itemNameField;
    private EditText shortDescriptionField;
    private EditText fullDescriptionField;
    private EditText valueField;
    private Spinner categorySpinner;

    // Other
    private Location location;
    private static final String TAG = "NewItemActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Activity started");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);
        location = Model.getInstance().getLoggedInUser().getLocation(); // TODO: what if not a location employee?
        guiObjects();
        setUpCancelButton();
        setUpSubmitButton();
        setUpSpinner();
        Log.d(TAG, "Set up done");
    }

    private void setUpSpinner() {
        categorySpinner = findViewById(R.id.new_item_spinner);
        List<String> categoryNames = new ArrayList<>();
        for (ItemCategory c: ItemCategory.getCurrentCategories()) {
            categoryNames.add(c.getCategoryName());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);
    }

    private void setUpCancelButton() {
        cancelButton = findViewById(R.id.new_item_cancel_button);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setUpSubmitButton() {
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!itemNameField.getText().toString().isEmpty()) {
                    Item item = new Item(itemNameField.getText().toString(), location);
                    if (!valueField.getText().toString().isEmpty()) {
                        item.setCents((int) (Double.parseDouble(valueField.getText().toString()) * 100));
                    } else {
                        item.setCents(0);
                    }
                    item.setCreator(Model.getInstance().getLoggedInUser().getUsername());
                    item.setTimeStamp(new Date());
                    item.setFullDescription(fullDescriptionField.getText().toString());
                    item.setShortDescription(shortDescriptionField.getText().toString());
                    String cat = categorySpinner.getSelectedItem().toString();
                    item.setCategory(ItemCategory.getCategoryByName(cat));
                    location.addItem(item);
                    finish();
                } else {
                    throw new IllegalArgumentException("must fill in name");
                }
            }
        });
    }

    private void guiObjects() {
        cancelButton = findViewById(R.id.new_item_cancel_button);
        submitButton = findViewById(R.id.new_item_submit_button);
        itemNameField = findViewById(R.id.new_item_name);
        shortDescriptionField = findViewById(R.id.new_item_short_description);
        fullDescriptionField = findViewById(R.id.new_item_full_description);
        valueField = findViewById(R.id.new_item_value);
        categorySpinner = findViewById(R.id.new_item_spinner);
    }

}
