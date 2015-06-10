package com.slashandhyphen.tasterly.FlavorControl;

import android.content.Context;
import android.widget.RelativeLayout;

import com.slashandhyphen.tasterly.FlavorControl.Flavors.Flavor;

/**
 * This is a basic test to try to emulate what I have in the AddBeerFragment at the moment.  At some
 * point, one geomancy class will probably be needed for each node, and then they call all be
 * controlled by some central "Geomancer" in order to determine space available, collisions...
 */
public class OneRingGeomancy extends Geomancy {

    float originX, originY;
    int viewDiameter, buttonRadius;
    double drawRadius;

    public OneRingGeomancy(Context context, RelativeLayout pLayout, Flavor[] flavors) {
        super(context, pLayout, flavors);

        originX = pLayout.getWidth() / 2;
        originY = pLayout.getHeight() / 2;
        setDimensions();
    }

    private void setDimensions() {
        if(pLayout.getHeight() < pLayout.getWidth())
            viewDiameter = pLayout.getHeight();
        else
            viewDiameter = pLayout.getWidth();

        buttonRadius = viewDiameter / 2 - flavors[0].getHeight();
        drawRadius = buttonRadius / 1.5;

        for(int i = 0; i < flavors.length; ++i) {
            double theta = ((2 * Math.PI) / flavors.length) * i;
            flavors[i].
                    setX((float)((originX + drawRadius * Math.cos(theta + Math.PI)) -
                            flavors[i].getHeight() / 2));
            flavors[i].
                    setY((float) ((originY + drawRadius *
                            (float) Math.sin(theta + Math.PI)) - flavors[i].getHeight() / 2));
        }
    }

    public void geomatize(Flavor[] primaryFlavors) {

    }
}
