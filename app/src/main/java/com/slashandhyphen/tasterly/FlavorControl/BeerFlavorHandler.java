package com.slashandhyphen.tasterly.FlavorControl;

import android.content.Context;
import android.widget.RelativeLayout;

import com.slashandhyphen.tasterly.FlavorControl.Flavors.*;


/**
 * Created by ookamijin on 6/9/2015.
 */
public class BeerFlavorHandler extends FlavorHandler {
    Flavor primaryFlavors[];

    public BeerFlavorHandler(Context context, RelativeLayout layout) {
        super(context, layout);

        initializeFlavorObjects();
    }

    private void initializeFlavorObjects() {

        // TODO Don't do this:
        primaryFlavors = new Flavor[8];
        primaryFlavors[0] = new FlavorAlcohol(context, layout);
        primaryFlavors[1] = new FlavorFaults(context, layout);
        primaryFlavors[2] = new FlavorHops(context, layout);
        primaryFlavors[3] = new FlavorMalt(context, layout);
        primaryFlavors[4] = new FlavorMouthfeel(context, layout);
        primaryFlavors[5] = new FlavorSugar(context, layout);
        primaryFlavors[6] = new FlavorWater(context, layout);
        primaryFlavors[7] = new FlavorYeast(context, layout);

    }

    @Override
    public void showContent() {

        for(Flavor flav : primaryFlavors) {
            flav.showContent();
        }

        geomancy = new OneRingGeomancy(context, layout, primaryFlavors);
    }

    @Override
    public void saveOrigin() {
        for(Flavor flav : primaryFlavors) {
            flav.saveOrigin();
        }
    }

    @Override
    public void moveOrigin(float dX, float dY) {
        for(Flavor flav : primaryFlavors) {
            flav.moveOrigin(dX, dY);
        }
    }

}
