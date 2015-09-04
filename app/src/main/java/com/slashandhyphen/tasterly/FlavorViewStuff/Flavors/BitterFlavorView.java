package com.slashandhyphen.tasterly.FlavorViewStuff.Flavors;

import android.content.Context;

import com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView;
import com.slashandhyphen.tasterly.R;

/**
 * Created by ookamijin on 8/27/2015.
 */
public class BitterFlavorView extends FlavorView {
    public BitterFlavorView(Context context) {
        super(context);
        icon.setBackgroundResource(R.drawable.flavor_icon_bitter);
        label.setText("Bitter");
    }
}
