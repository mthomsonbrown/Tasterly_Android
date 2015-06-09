package com.slashandhyphen.tasterly.Flavors;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * This is a basic test to try to emulate what I have in the AddBeerFragment at the moment.  At some
 * point, one geomancy class will probably be needed for each node, and then they call all be
 * controlled by some central "Geomancer" in order to determine space available, collisions...
 */
public class OneRingGeomancy extends Geomancy {

    float originX, originY;
    int viewDiameter, buttonRadius;

    public OneRingGeomancy(Context context, RelativeLayout pLayout) {
        super(context, pLayout);

        setDimensions();
    }

    private void setDimensions() {
        if(pLayout.getHeight() < pLayout.getWidth())
            viewDiameter = pLayout.getHeight();
        else
            viewDiameter = pLayout.getHeight();

        buttonRadius = viewDiameter / 2; // - flavorButtons.property <- Oh God!  how to do this part?
    }
}
