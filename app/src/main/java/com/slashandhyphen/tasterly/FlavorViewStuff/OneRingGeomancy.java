package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;

import java.util.ArrayList;

/**
 * Created by ookamijin on 7/1/2015.
 *
 * This is a basic test to try to emulate what I have in the AddBeerFragment at the moment.  At some
 * point, one geomancy class will probably be needed for each node, and then they can all be
 * controlled by some central "Geomancer" in order to determine space available, collisions...
 */
public class OneRingGeomancy extends Geomancy {
    static String TAG = "OneRingGeometry";
    int viewDiameter, maxRadius;
    double drawRadius;

    /**
     * Constructor currently defers all work to parent class
     *
     * @param origin A view whose X/Y coordinates are used as a point of reference for organizing
     *               the FlavorView objects
     * @param pLayout A reference to the layout the FlavorViews will be contained within
     */
    public OneRingGeomancy(FlavorView origin, RelativeLayout pLayout) {
        super(origin, origin.getChildren(), pLayout);
    }

    /**
     * Calculates a minimum circumference for a ring of FlavorView objects based on the amount of
     * space each view would take on a circle given the FlavorView's current dimensions and its
     * index number in the array, determining its radian value.  (index 0 gets rad 0, etc.)
     */
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

    /**
     * calculates the maximum diameter available for a circle
     *
     * @param v The view what's calling OneRingGeomancy
     * @return An integer for the maximum diameter, assuming no padding values
     */
    private int getMaxSquare(View v) {
        if(v.getHeight() < v.getWidth())
            return v.getWidth();
        else
            return v.getHeight();
    }

    /**
     * Creates an array the length of the FlavorView array containing radian values evenly
     * distributed around a circle.  This does not guarantee location for FlavorView objects, just
     * even spacing.
     *
     * @param v The array list of FlavorViews (This could be generalized to just accept an int,
     *          which would be simply the size of the FlavorView array.  No other information is
     *          currently needed from the array).
     * @return An array of doubles representing the radians to assign to each view.
     */
    private double[] getChildThetaArray(ArrayList<FlavorView> v) {
        double theta[] = new double[v.size()];
        for(int i = 0; i < v.size(); ++i) {
            theta[i] = ((2 * Math.PI) / v.size()) * i;
        }
        return theta;
    }

    /**
     * Currently calculates the length of a straight line intersecting a view at its origin,
     * representing a tangent based on the position of the view on a circle.
     *
     * <Note>
     *     Since view and theta are loosely coupled, this can possible attribute the wrong theta
     *     information to a view.
     * </Note>
     *
     * @param v The view being measured
     * @param theta The position the view is on a circle
     * @return The length of the line that intersects the view based on its position in the cirlce
     */
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
