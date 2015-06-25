package com.slashandhyphen.tasterly.FlavorControl.Flavors;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/9/2015.
 */
public class FlavorMouthfeel extends Flavor {
    public FlavorMouthfeel(Context context, RelativeLayout pLayout) {
        super(context, pLayout);
    }

    @Override
    public void initializeFlavorButton() {
        flavorButton.setText("MO");
    }
}