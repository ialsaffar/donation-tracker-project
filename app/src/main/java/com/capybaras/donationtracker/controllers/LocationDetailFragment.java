package com.capybaras.donationtracker.controllers;

import android.app.Activity;
import android.support.annotation.NonNull;
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
    private final Model model = Model.getInstance();
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private Location mItem;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if ((getArguments() != null) && getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = model.getLocationMap()
                    .get(Integer.parseInt(getArguments().getString(ARG_ITEM_ID)));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = null;
            if (activity != null) {
                appBarLayout = activity.findViewById(R.id.toolbar_layout);
            }
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.getName());
            }
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.location_detail, container, false);

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            String details = mItem.getName() + "\n" +
                    "[" + mItem.getType() + "]\n\n" +
                    mItem.getPhone() + "\n" +
                    mItem.getWebsite() + "\n\n" +
                    mItem.getStreetAddress() + "\n" +
                    mItem.getCity() + ", " + mItem.getState() + " " + mItem.getZipCode() + "\n" +
                    mItem.getLatitude() + "°, " + mItem.getLongitude() + "°\n";
            ((TextView) rootView.findViewById(R.id.location_detail)).setText(details);
        }

        return rootView;
    }
}
