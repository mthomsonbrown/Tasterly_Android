package com.slashandhyphen.tasterly.FlavorControl;

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

    public abstract void showContent();

    public abstract void saveOrigin();

    public abstract void moveOrigin(float dX, float dY);
}
