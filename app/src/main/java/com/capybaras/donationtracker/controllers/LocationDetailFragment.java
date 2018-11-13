package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Location;
import com.capybaras.donationtracker.models.Model;

/**
 * A fragment representing a single Location detail screen.
 * This fragment is either contained in a {@link LocationListActivity}
 * in two-pane mode (on tablets) or a {@link LocationDetailActivity}
 * on handsets.
 */
public class LocationDetailFragment extends Fragment {
    Model model = Model.getInstance();
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Location mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = model.getLocationMap().get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            StringBuilder details = new StringBuilder();
            details.append(mItem.getName() + "\n");
            details.append("[" + mItem.getType() + "]\n\n");
            details.append(mItem.getPhone() + "\n");
            details.append(mItem.getWebsite() + "\n\n");
            details.append(mItem.getStreetAddress() + "\n");
            details.append(mItem.getCity() + ", " + mItem.getState() + " " + mItem.getZipCode() + "\n");
            details.append(mItem.getLatitude() + "°, " + mItem.getLongitude() + "°\n");
            ((TextView) rootView.findViewById(R.id.location_detail)).setText(details.toString());
        }

        return rootView;
    }
}
