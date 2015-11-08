package com.slashandhyphen.tasterly.Models;

/**
 * Created by ookamijin on 11/7/2015.
 */
public class Flavor {
    private String name;
    private Integer rating;

    public Flavor(String name, Integer rating) {
        this.name = name;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }
}
