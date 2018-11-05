package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.LocationList;
import com.capybaras.donationtracker.models.MapDataService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import com.capybaras.donationtracker.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private static final int REQUEST_CODE = 1001;
    private LatLng currentLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                currentLocation = latLng;

                Intent intent = new Intent(getApplicationContext(), NewLocationForm.class);
                startActivityForResult(intent, REQUEST_CODE);

            }
        });

        //Get the list of locations and add a marker for each
        LocationList locations = new LocationList();

        for (Location curr : locations.getLocations()) {
            LatLng newlatlng = new LatLng(curr.getLatitude(), curr.getLongitude());
            mMap.addMarker(new MarkerOptions().position(newlatlng).title(curr.getName()).snippet(curr.getDescription()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newlatlng));
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        final MapDataService mapDataService = MapDataService.getInstance().getInstance();

        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String nameOfLocation = data.getStringExtra(NewLocationForm.NAME_CODE);
                    String website = data.getStringExtra(NewLocationForm.WEBSITE_CODE);
                    String phone = data.getStringExtra(NewLocationForm.PHONE_CODE);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(currentLocation);

                    mapDataService.addMapDataElement(nameOfLocation, website + "\n" + phone, new Location(currentLocation.latitude, currentLocation.longitude));

                    markerOptions.title(mapDataService.getLastElementAdded().getName());
                    markerOptions.snippet(mapDataService.getLastElementAdded().getDescription());

                    mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLocation));

                    mMap.addMarker(markerOptions);
                }
        }
    }
}
