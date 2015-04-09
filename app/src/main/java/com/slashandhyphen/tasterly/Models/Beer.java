package com.slashandhyphen.tasterly.Models;

import com.orm.SugarRecord;

import java.util.HashMap;

/**
 * Created by ookamijin on 3/6/2015.
 */
public class Beer  extends SugarRecord<Beer> {
    private String name;
    private HashMap<String, Integer> flavors;

    public Beer() {
        flavors = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String listFlavors() {

        return flavors.toString();
    }

    public HashMap<String, Integer> getFlavors() {
        return flavors;
    }

    public void addFlavor(String flavorName, int flavorRating) {

        flavors.put(flavorName, flavorRating);
    }
}
