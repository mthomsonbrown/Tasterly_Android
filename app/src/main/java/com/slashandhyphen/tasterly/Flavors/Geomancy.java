package com.slashandhyphen.tasterly.Flavors;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * This class handles calculating the layout of arrays of flavor objects.
 */
public abstract class Geomancy {
    Context context;
    RelativeLayout pLayout;

    public Geomancy(Context context, RelativeLayout pLayout) {
        this.context = context;
        this.pLayout = pLayout;

    }
}
