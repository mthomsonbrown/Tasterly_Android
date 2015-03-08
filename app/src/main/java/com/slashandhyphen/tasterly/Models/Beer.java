package com.slashandhyphen.tasterly.Models;

import com.orm.SugarRecord;

/**
 * Created by ookamijin on 3/6/2015.
 */
public class Beer  extends SugarRecord<Beer> {
    private String name;
    private long goodness;

    public Beer() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGoodness() {
        return goodness;
    }

    public void setGoodness(long goodness) {
        this.goodness = goodness;
    }



}
