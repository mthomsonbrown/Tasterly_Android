package com.slashandhyphen.tasterly.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ookamijin on 3/6/2015.
 *
 * Container for data relating to one beer entry by one user.
 */
public class Beer {
    private String name;
    private float abv;
    ArrayList<Flavor> flavors;

    public Beer() {
        flavors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getAbv() {
        return abv;
    }

    public void setAbv(float abv) {
        this.abv = abv;
    }

    public ArrayList<Flavor> getFlavors() {
        return flavors;
    }

    public void addFlavors(HashMap<String, Integer> flavorHash) {
        for(Map.Entry<String, Integer> flavor : flavorHash.entrySet()) {
            this.flavors.add(new Flavor(flavor.getKey(), flavor.getValue()));
        }
    }

    public void addFlavor(Flavor flav) {
        flavors.add(flav);
    }

}
