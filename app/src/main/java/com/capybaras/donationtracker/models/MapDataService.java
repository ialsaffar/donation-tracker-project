package com.capybaras.donationtracker.models;


import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertwaters on 3/13/18.
 *
 * This is a Controller for UI elements that need access to the data
 *
 * Implements the Facade and Singleton patterns.
 *
 * Primarily interacts the DataManager model class
 */

public class MapDataService {
    private static MapDataService INSTANCE = new MapDataService();
    public static MapDataService getInstance() { return INSTANCE; }

    private MapDataElement theLastAddedElement;

    private List<MapDataElement> data = new ArrayList<>();


    /**
     * get a list of all the data
     * @return  the full list of data
     */
    public List<MapDataElement> getData() {
        return data;
    }

    /**
     * Add a new data element to the model
     * @param name   the name of the element
     * @param desc   textual description of the element
     * @param loc    location of the element
     */
    public void addMapDataElement(String name, String desc, Location loc) {
        MapDataElement mapElement = new MapDataElement(name, desc, loc);
        data.add(mapElement);
        theLastAddedElement = mapElement;
    }


    /**
     * Return the last element added.  This method is mainly to support UI
     * @return the last element added to the model.
     */
    public MapDataElement getLastElementAdded() {
        return theLastAddedElement;
    }
}

