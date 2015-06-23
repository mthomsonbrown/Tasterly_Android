package com.slashandhyphen.tasterly.FlavorControl;

import android.content.Context;
import android.widget.RelativeLayout;

import com.slashandhyphen.tasterly.FlavorControl.Flavors.Flavor;

/**
 * This class handles calculating the layout of arrays of flavor objects.
 */
public abstract class Geomancy {
    Context context;
    RelativeLayout pLayout;
    Flavor[] flavors;

    public Geomancy(Context context, RelativeLayout pLayout, Flavor[] flavors) {
        this.context = context;
        this.pLayout = pLayout;
        this.flavors = flavors;

    }

}
