package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * This is a basic test to try to emulate what I have in the AddBeerFragment at the moment.  At some
 * point, one geomancy class will probably be needed for each node, and then they can all be
 * controlled by some central "Geomancer" in order to determine space available, collisions...
 */
public class OneRingGeomancy extends Geomancy {
    static String TAG = "OneRingGeometry";
    int viewDiameter, maxRadius;
    double drawRadius;

    public OneRingGeomancy(FlavorView origin, RelativeLayout pLayout) {
        super(origin, origin.getChildren(), pLayout);
    }

    public void setDimensions() {
        viewDiameter = getMaxSquare(pLayout);
        double minCirc = 0;

        double theta[] = getChildThetaArray(children);

        for(int i = 0; i < children.size(); ++i) {
            minCirc += getTangentLength(children.get(i), theta[i]);
        }

        double radius = minCirc / (2 * Math.PI);

        maxRadius = viewDiameter / 2 - children.get(0).getWidth();
        drawRadius = maxRadius / 1.5;

        for(View child : children) {
            child.
                    setX((float) ((origin.getX() + radius *
                            Math.cos(theta[children.indexOf(child)] + Math.PI)) -
                            child.getWidth() / 2));
            child.
                    setY((float) ((origin.getY() + radius *
                            (float) Math.sin(theta[children.indexOf(child)] + Math.PI)) -
                            child.getHeight() / 2));
        }
    }

    private int getMaxSquare(View v) {
        if(v.getHeight() < v.getWidth())
            return v.getWidth();
        else
            return v.getHeight();
    }

    private double[] getChildThetaArray(ArrayList<FlavorView> v) {
        double theta[] = new double[v.size()];
        for(int i = 0; i < v.size(); ++i) {
            theta[i] = ((2 * Math.PI) / v.size()) * i;
        }
        return theta;
    }

    private double getTangentLength(View v, double theta) {
        double tanRad = theta + Math.PI / 2;

        double m = Math.tan(tanRad);
        double lX, lY;
        if (v.getHeight() / m > v.getWidth()) {
            lX = v.getWidth();
            lY = m * v.getWidth();
        }
        else {
            lY = v.getHeight();
            lX = v.getHeight() / m;
        }

        return Math.sqrt(Math.pow(lX, 2) + Math.pow(lY, 2));

    }

}
