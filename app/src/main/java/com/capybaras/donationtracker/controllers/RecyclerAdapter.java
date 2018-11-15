package com.capybaras.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.ItemCategory;
import com.capybaras.donationtracker.models.Location;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * RecyclerAdapter class
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private final Location location;
    private final List<Item> items;
    private final List<String> mItemNames = new ArrayList<>();
    //private List<Image> mImages = new ArrayList<>();
    private final Context mContext;

    /**
     * RecyclerAdapter constructor
     * @param mContext the context
     * @param location the location
     * @param category the item category
     * @param itemName the name of the item
     */
    public RecyclerAdapter(Context mContext, Location location, ItemCategory category, String itemName) {
        this.location = location;
        this.mContext = mContext;
        this.items = Location.getItems();
        for (Item i : items) {
            mItemNames.add(i.getName());
            //mImages.add(i.getImage());
        }
        if (category != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).getCategory() != category) {
                    mItemNames.remove(i);
                    items.remove(i);
                    //mImages.remove(i);
                }
            }
        }
        if ((itemName != null) && (!itemName.equals(""))) {
            for (int i = 0; i < mItemNames.size(); i++) {
                if (!(mItemNames.get(i).equals(itemName))){
                    mItemNames.remove(i);
                    items.remove(i);
                    //mImages.remove(i);
                }
            }
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_recycler_view, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called");

        viewHolder.textView.setText(mItemNames.get(i));
        viewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick clicked" + mItemNames.get(i));
                Intent intent = new Intent(mContext, ItemDetailsActivity.class);
                intent.putExtra("item_id", "" + items.get(i).getId());
                intent.putExtra("location_key", "" + location.getKey());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItemNames.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @SuppressWarnings("unused")
        final CircleImageView image;
        final TextView textView;
        final RelativeLayout relativeLayout;

        /**
         * ViewHolder constructor
         * @param itemView the specific view
         */
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.item_name);
            relativeLayout = itemView.findViewById(R.id.item_recycler_view_layout);
        }
    }
}