package com.capybaras.donationtracker.controllers;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.ItemCategory;
import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.Model;
import com.capybaras.donationtracker.models.User;
import com.capybaras.donationtracker.models.UserTypes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ItemListActivity extends Activity {

    private static final String TAG = "ItemListActivity";
    private Spinner spinner;
    private Spinner sortByCategory;
    private EditText searchItemName;
    private ItemCategory selectedCategory;
    private Location selectedLocation;
    private String itemSearch;
    private RecyclerView recyclerView;
    private Button addItemButton;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        user = Model.getInstance().getLoggedInUser();
        setUpSpinner();
        setUpButton();
        setUpSortSpinnerCategory();
        setUpItemSearch();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setUpRecycler();

    }

    public String getLocationName() {
        return selectedLocation.getName();
    }

    private void setUpItemSearch() {
        searchItemName = findViewById(R.id.editText);

        searchItemName.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                itemSearch = s.toString();
                setUpRecycler();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                itemSearch = s.toString();
                setUpRecycler();
            }

            @Override
            public void afterTextChanged(Editable s) {
                itemSearch = s.toString();
                setUpRecycler();
            }
        });
    }

    private void setUpSortSpinnerCategory() {
        sortByCategory = findViewById(R.id.itemTypeSpinner);
        final List<ItemCategory> sortCategoryList = new ArrayList<>();
        sortCategoryList.add(null);
        for (int j = 0; j < ItemCategory.getCurrentCategories().size(); j++) {
            sortCategoryList.add(ItemCategory.getCurrentCategories().get(j));
        }
        List<String> categoryNames = new ArrayList<>();
        categoryNames.add("Item Type");
        for(int i = 1; i < sortCategoryList.size(); i++) {
            categoryNames.add(sortCategoryList.get(i).getCategoryName());
        }
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, categoryNames);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sortByCategory.setAdapter(categoryAdapter);

        sortByCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedCategory = sortCategoryList.get(position);
                setUpRecycler();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addItemButton.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }

    private void setUpSpinner() {
        spinner = findViewById(R.id.spinner);
        final List<Location> locationList = new ArrayList<>();
        locationList.add(null);
        for (int j = 0; j < Model.getInstance().getLocations().size(); j++) {
            locationList.add(Model.getInstance().getLocations().get(j));
        }
        List<String> locationNameList = new LinkedList<>();
        locationNameList.add("All Items");
        for (int i = 1; i < locationList.size(); i++) {
            System.out.println(locationList.get(i).getName());
            locationNameList.add(locationList.get(i).getName());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locationNameList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d(TAG, "Username: " + user.getUsername());
                if ((Model.getInstance().getLoggedInUser().getType() == UserTypes.LOCATION_EMPLOYEE)
                    && (Model.getInstance().getLoggedInUser().getLocation() != null)){
                    Log.d(TAG, Model.getInstance().getLoggedInUser().getLocation().getName());
                } else {
                    Log.d(TAG, locationList.get(0).getName());
                }
                selectedLocation = locationList.get(position);
                if (selectedLocation != null) {
                    System.out.println("User Location: " + user.getLocation().getName());
                    if (user.getType() == UserTypes.LOCATION_EMPLOYEE
                            && selectedLocation.equals(user.getLocation())) {
                        addItemButton.setVisibility(View.VISIBLE);
                    } else {
                        addItemButton.setVisibility(View.GONE);
                    }
                }
                setUpRecycler();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                addItemButton.setVisibility(View.GONE);
                recyclerView.setVisibility(View.GONE);
            }
        });
    }

    private void setUpRecycler() {
        recyclerView = findViewById(R.id.recycler);
        RecyclerAdapter adapter = new RecyclerAdapter(this, selectedLocation, selectedCategory, itemSearch);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setUpButton() {
        addItemButton = findViewById(R.id.add_item_button);
        addItemButton.setVisibility(View.GONE);
        addItemButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewItemActivity.class);
                startActivity(intent);
                Log.d(TAG, "Button clicked");
            }
        });
        Log.d(TAG, "Button on click listener set up");
    }

    private Activity getActivity(){
        return this;
    }

    public Location getSelectedLocation() {
        return selectedLocation;
    }

}
