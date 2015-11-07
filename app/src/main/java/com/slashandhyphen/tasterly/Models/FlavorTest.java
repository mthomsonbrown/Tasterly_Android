package com.slashandhyphen.tasterly.Models;

/**
 * Created by ookamijin on 11/6/2015.
 */
public class FlavorTest {

    String name;
    int rating;

    FlavorTest(String name, int rating) {
        this.name = name;
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

}
