package com.slashandhyphen.tasterly.FlavorControl.Flavors;

import android.content.Context;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/9/2015.
 */
public class FlavorFaults extends Flavor {
    public FlavorFaults(Context context, RelativeLayout pLayout) {
        super(context, pLayout);
    }

    @Override
    public void initializeFlavorButton() {
        flavorButton.setText("F");
    }
}