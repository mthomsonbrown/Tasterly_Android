package com.slashandhyphen.tasterly.Models;

import java.util.ArrayList;

/**
 * Created by ookamijin on 11/5/2015.
 */
public class BeerTest {
    String name = "Beer Name";
    int abv = 100;
    ArrayList<FlavorTest> flavor;

    public BeerTest() {
        flavor = new ArrayList<>();
        flavor.add(new FlavorTest("oneName", 42));
        flavor.add(new FlavorTest("twoName", 1134));
    }
}
