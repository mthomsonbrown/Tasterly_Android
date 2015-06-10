package com.slashandhyphen.tasterly.FlavorControl.Flavors;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/5/2015.
 */
public class FlavorFlav extends Flavor {

    public FlavorFlav(Context context, RelativeLayout pLayout) {
        super(context, pLayout);

        flavorButton.setText("FLAVA FLAV!!!1!");
    }
}
