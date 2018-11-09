package com.capybaras.donationtracker.models;

import java.util.ArrayList;
import java.util.List;

public class ItemCategory {
    private static List<ItemCategory> currentCategories = generateCategories();
    private String categoryName;
    private String categoryDescription;

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

    public static ItemCategory newCategory(String name, String description) {
        ItemCategory cat = new ItemCategory(name, description);
        currentCategories.add(cat);
        return cat;
    }

    public static ItemCategory newCategory(String name) {
        return newCategory(name, "");
    }

    public static List<ItemCategory> getCurrentCategories() {
        return currentCategories;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getCategoryDescription() {
        return categoryDescription;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setCategoryDescription(String categoryDescription) {
        this.categoryDescription = categoryDescription;
    }

    public static void setCurrentCategories(List<ItemCategory> currentCategories) {
        ItemCategory.currentCategories = currentCategories;
    }
}
