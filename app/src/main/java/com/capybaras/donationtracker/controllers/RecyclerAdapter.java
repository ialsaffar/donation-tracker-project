package com.capybaras.donationtracker.controllers;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.capybaras.donationtracker.R;
import com.capybaras.donationtracker.models.Item;
import com.capybaras.donationtracker.models.ItemCategory;
import com.capybaras.donationtracker.models.Location;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private static final String TAG = "RecyclerAdapter";
    private Location location;
    private List<Item> items = new ArrayList<>();;
    private List<String> mItemNames = new ArrayList<>();
    //private List<Image> mImages = new ArrayList<>();
    private Context mContext;

    public RecyclerAdapter(Context mContext, Location location, ItemCategory category, String itemName) {
        this.location = location;
        this.mContext = mContext;
        if (location == null) {
            this.items = Location.getItems();
            for (Item i : items) {
                mItemNames.add(i.getName());
                //mImages.add(i.getImage());
            }
        } else {
            List<Item> itemList = Location.getItems();
            for (Item i : itemList) {
                if (i.getLocation() == location) {
                    mItemNames.add(i.getName());
                    items.add(i);
                    //mImages.add(i.getImage());
                }
            }
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
        if ((itemName != null) && (itemName != "")) {
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        CircleImageView image;
        TextView textView;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.item_name);
            relativeLayout = itemView.findViewById(R.id.item_recycler_view_layout);
        }
    }
}