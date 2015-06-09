package com.slashandhyphen.tasterly.Flavors;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/9/2015.
 */
public class BeerFlavorHandler extends FlavorHandler {

    public BeerFlavorHandler(Context context, RelativeLayout layout) {
        super(context, layout);

        geomancy = new OneRingGeomancy(context, layout);
    }

    public void showButtons() {
        Flavor flav = new FlavorFlav(context, layout);
        flav.showButton();
    }
}
