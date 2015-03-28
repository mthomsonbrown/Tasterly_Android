package com.slashandhyphen.tasterly.Models;

import com.orm.SugarRecord;

/**
 * Created by ookamijin on 3/6/2015.
 */
public class Beer  extends SugarRecord<Beer> {
    private String name;
    private String flavorNames[];

    public Beer() {
        flavorNames = new String[51];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getFlavorNames() {
        return flavorNames;
    }

    public void setFlavorNames(String[] flavorNames) {
        this.flavorNames = flavorNames;
    }
}
