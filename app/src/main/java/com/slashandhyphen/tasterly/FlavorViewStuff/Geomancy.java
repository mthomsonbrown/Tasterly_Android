package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * This class handles calculating the layout of arrays of flavor objects.
 */
public abstract class Geomancy {
    RelativeLayout pLayout;
    View origin;
    ArrayList<FlavorView> children;

    public Geomancy(View origin, ArrayList<FlavorView> children, RelativeLayout pLayout) {
        this.origin = origin;
        this.children = children;
        this.pLayout = pLayout;

    }

}
