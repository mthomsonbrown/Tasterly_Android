package com.slashandhyphen.tasterly.Flavors;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * This class handles all of the flavor objects in a nodal structure.  It also contains a geomancy
 * object that calculates the positions each object should have on the screen.
 */
public abstract class FlavorHandler {
    Context context;
    RelativeLayout layout;
    OneRingGeomancy geomancy;

    public FlavorHandler(Context context, RelativeLayout layout) {
        this.context = context;
        this.layout = layout;
    }

    public abstract void showButtons();
}
