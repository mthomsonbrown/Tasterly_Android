package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * This is a basic test to try to emulate what I have in the AddBeerFragment at the moment.  At some
 * point, one geomancy class will probably be needed for each node, and then they can all be
 * controlled by some central "Geomancer" in order to determine space available, collisions...
 */
public class OneRingGeomancy extends Geomancy {

    int viewDiameter, maxRadius;
    double drawRadius;

    public OneRingGeomancy(View origin, ArrayList<View> children, RelativeLayout pLayout) {
        super(origin, children, pLayout);

        setDimensions();
    }

    public OneRingGeomancy(FlavorView origin, RelativeLayout pLayout) {
        super(origin, origin.getChildren(), pLayout);
    }

    public void setDimensions() {
        if(pLayout.getHeight() < pLayout.getWidth())
            viewDiameter = pLayout.getHeight();
        else
            viewDiameter = pLayout.getWidth();

        // TODO should find largest child, not just use first...
        maxRadius = viewDiameter / 2 - children.get(0).getHeight();
        drawRadius = maxRadius / 1.5;

        for(View child : children) {
            double theta = ((2 * Math.PI) / children.size()) * children.indexOf(child);
            child.
                    setX((float)((origin.getX() + drawRadius * Math.cos(theta + Math.PI)) -
                            child.getHeight() / 2));
            child.
                    setY((float) ((origin.getY() + drawRadius *
                            (float) Math.sin(theta + Math.PI)) - child.getHeight() / 2));
        }
    }
}
