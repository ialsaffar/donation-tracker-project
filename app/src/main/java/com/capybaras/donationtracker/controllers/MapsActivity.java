package com.capybaras.donationtracker.controllers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.Model;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.capybaras.donationtracker.R;

/**
 * MapsActivity class: Creates a google map with current locations
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final float ZOOM = 12.0f;
    private GoogleMap mMap;
    private Model model;
    //Longitude and Latitude of the default zoom location of the map.
    //Currently the center of Atlanta.
    private final LatLng DEFAULT_MAP_POSITION = new LatLng(33.7490, -84.3880);

    private LatLng currentLocation = new LatLng(37, -85);
    private static final int REQUEST_CODE = 1010101;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        model = Model.getInstance();
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
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(DEFAULT_MAP_POSITION, ZOOM));
        for(Location location : model.getLocations()){
            MarkerOptions markerOptions = new MarkerOptions();
            markerOptions.position(new LatLng(location.getLatitude(), location.getLongitude()));
            markerOptions.title(location.getName());
            markerOptions.snippet(location.getPhone() + "\n" + location.getType());
            mMap.addMarker(markerOptions);
        }
        //Use a custom layout for the pin data
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());

        // Setting a click event handler for the map
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {

            @Override
            public void onMapClick(LatLng latLng) {
                currentLocation = latLng;

                Intent intent = new Intent(getApplicationContext(), NewLocationForm.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    String nameOfLocation = data.getStringExtra(NewLocationForm.NAME_CODE);
                    String website = data.getStringExtra(NewLocationForm.WEBSITE_CODE);
                    String phone = data.getStringExtra(NewLocationForm.PHONE_CODE);

                    MarkerOptions markerOptions = new MarkerOptions();
                    markerOptions.position(currentLocation);

                    markerOptions.title(nameOfLocation);
                    markerOptions.snippet(phone + "\n" + website);

                    mMap.animateCamera(CameraUpdateFactory.newLatLng(currentLocation));

                    mMap.addMarker(markerOptions);
                }
        }
    }

    /**
     * This class implements a custom layout for the pin
     */
    final class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        /**
         * Make the adapter
         */
        @SuppressLint("InflateParams")
        CustomInfoWindowAdapter(){
            // hook up the custom layout view in res/custom_map_pin_layout.xml
            myContentsView = getLayoutInflater().inflate(R.layout.custom_map_pin_layout, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = (myContentsView.findViewById(R.id.title));
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = (myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            //Auto-generated method stub
            return null;
        }

    }
}
