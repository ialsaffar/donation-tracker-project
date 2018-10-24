package com.capybaras.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.Model;

public class ItemDetailsActivity extends Activity {

    private TextView nameView;
    private TextView shortDescriptionView;
    private TextView fullDescriptionView;
    private TextView categoryView;
    private TextView valueView;
    private TextView timestampView;
    private TextView locationView;
    private TextView employeeView;

    private Item item;
    private int itemId;
    private int locationKey;
    private static final String TAG = "ItemDetailsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);
        getIncomingIntent();
        item = Model.getInstance().getLocationByKey(locationKey).getItemById(itemId);

        nameView = findViewById(R.id.details_name);
        nameView.setText("Name: " + item.getName());

        shortDescriptionView = findViewById(R.id.details_short_description);
        shortDescriptionView.setText(item.getShortDescription());

        fullDescriptionView = findViewById(R.id.details_full_description);
        fullDescriptionView.setText(item.getFullDescription());

        categoryView = findViewById(R.id.details_category);
        categoryView.setText("Category: " + item.getCategory().getCategoryName());

        valueView = findViewById(R.id.details_value);
        valueView.setText("Value: $" + String.format("%.2f", item.getCents()/100.0));

        timestampView = findViewById(R.id.details_date);
        String timestamp = "Timestamp: ";
        timestamp += item.getTimeStamp().getMonth() + "/";
        timestamp += item.getTimeStamp().getDay() + "/";
        timestamp += item.getTimeStamp().getYear() + ", ";
        timestamp += item.getTimeStamp().getHours() + ":";
        timestamp += item.getTimeStamp().getMinutes();
        timestampView.setText(timestamp);

        locationView = findViewById(R.id.details_locatoin);
        locationView.setText("Location: " + item.getLocation().getName());

        employeeView = findViewById(R.id.details_employee);
        employeeView.setText("Added by: " + item.getCreator());
    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("item_id") && getIntent().hasExtra("location_key")) {
            itemId = Integer.parseInt(getIntent().getStringExtra("item_id"));
            locationKey = Integer.parseInt(getIntent().getStringExtra("location_key"));
            //Log.d(TAG, "item id: " + getIntent().getStringExtra("item_id"));
            //Log.d(TAG, "loc key:" + getIntent().getStringExtra("location_key"));
        }
    }

}
