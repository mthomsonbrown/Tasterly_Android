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

    public void showContent() {

        for (int i = 0; i < primaryFlavors.length; ++i) {
            primaryFlavors[i].showContent();
        }

        geomancy = new OneRingGeomancy(context, layout, primaryFlavors);
    }
}
