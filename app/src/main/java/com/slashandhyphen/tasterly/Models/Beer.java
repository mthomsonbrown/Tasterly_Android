package com.slashandhyphen.tasterly.Models;

import java.util.HashMap;

/**
 * Created by ookamijin on 3/6/2015.
 *
 * Container for data relating to one beer entry by one user.
 */
public class Beer {
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

    public HashMap<String, Integer> getFlavors() {
        return flavors;
    }

    public void addFlavors(HashMap<String, Integer> flavorHash) {
        flavors = flavorHash;
    }
}
