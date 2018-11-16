package com.capybaras.donationtracker.models;

import java.util.ArrayList;
import java.util.List;

/**
 * ItemCategory class
 */
public final class ItemCategory {
    private static final List<ItemCategory> currentCategories = generateCategories();
    private final String categoryName;
    private final String categoryDescription;

    private static List<ItemCategory> generateCategories() {
        List<ItemCategory> list = new ArrayList<>();
        list.add(new ItemCategory("Clothes"));
        list.add(new ItemCategory("Food"));
        list.add(new ItemCategory("Kitchen"));
        list.add(new ItemCategory("Electronics"));
        list.add(new ItemCategory("Household"));
        list.add(new ItemCategory("Other"));
        return list;
    }

    private ItemCategory(String name, String description) {
        categoryName = name;
        categoryDescription = description;
    }

    /**
     * Gets the item's category by name
     * @param name a specific category name
     * @return the Item category
     */
    public static ItemCategory getCategoryByName(String name) {
        for (ItemCategory c: currentCategories) {
            if (c.getCategoryName().equals(name)) {
                return c;
            }
        }
        return newCategory(name);
    }

    private ItemCategory(String name) {
        this(name, "");
    }

    /**
     * Creates a new item category
     * @param name the name of the new category
     * @param description the new description of the category
     * @return the new Item Category
     */
    private static ItemCategory newCategory(String name, String description) {
        ItemCategory cat = new ItemCategory(name, description);
        currentCategories.add(cat);
        return cat;
    }

    /**
     * Creates a new Category without a description
     * @param name the name of the new category
     * @return the new Item Category
     */
    private static ItemCategory newCategory(String name) {
        return newCategory(name, "");
    }

    /**
     * Gets the current categories
     * @return all the current item categories
     */
    public static List<ItemCategory> getCurrentCategories() {
        return currentCategories;
    }

    /**
     * Gets the current category name
     * @return the name of the category
     */
    public String getCategoryName() {
        return categoryName;
    }

}
