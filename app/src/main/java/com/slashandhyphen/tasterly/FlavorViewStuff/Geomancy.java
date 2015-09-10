package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by ookamijin on 7/1/2015.
 *
 * This class handles calculating the layout of arrays of flavor objects.
 */
public abstract class Geomancy {
    RelativeLayout pLayout;
    View origin;
    ArrayList<FlavorView> children;

    /**
     * Gathers required materials to perform most geomancy
     *
     * @param origin A view whose X/Y coordinates are used as a point of reference for organizing
     *               the FlavorView objects
     * @param children An array of FlavorView objects that require positioning information
     * @param pLayout A reference to the layout the FlavorViews will be contained within
     */
    public Geomancy(View origin, ArrayList<FlavorView> children, RelativeLayout pLayout) {
        this.origin = origin;
        this.children = children;
        this.pLayout = pLayout;

    }

}
