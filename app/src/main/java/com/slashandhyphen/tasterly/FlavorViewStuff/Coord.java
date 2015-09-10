package com.slashandhyphen.tasterly.FlavorViewStuff;

/**
 * Created by ookamijin on 9/9/2015.
 *
 * <P>
 *     This class holds X and Y information for a view to be passed between a geomancer and an
 *     OmNomView.  It also has a boolean to check whether the coordinate information is for the
 *     middle of the view or the upper left corner.
 * </P>
 */
public class Coord {
    private int X, Y = 0;

    private boolean Center = false;

    /**
     * Gets X value
     * @return X value
     */
    public int getX() {
        return X;
    }

    /**
     * Set Y value
     * @param x X value
     */
    public void setX(int x) {
        X = x;
    }

    /**
     * Gets Y value
     * @return Y value
     */
    public int getY() {
        return Y;
    }

    /**
     * Set Y value
     * @param y Y value
     */
    public void setY(int y) {
        Y = y;
    }

    /**
     * Reports if the coordinate is a center value or a top left corner value
     *
     * @return true if center, false if top left
     */
    public boolean isCenter() {
        return Center;
    }

    /**
     * Sets whether the coordinate is for the center of the view, or the top left
     *
     * @param center true if center, false if top left
     */
    public void setCenter(boolean center) {
        Center = center;
    }
}
