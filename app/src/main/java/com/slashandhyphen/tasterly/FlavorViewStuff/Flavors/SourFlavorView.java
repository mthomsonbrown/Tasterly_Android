package com.slashandhyphen.tasterly.FlavorViewStuff.Flavors;

import android.content.Context;

import com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView;
import com.slashandhyphen.tasterly.R;

/**
 * Created by ookamijin on 8/27/2015.
 *
 * Extension of FlavorView for adding flavor-specific assets and possibly behavior/functionality
 */
public class SourFlavorView extends FlavorView {
    public SourFlavorView(Context context) {
        super(context);
        // TODO Not real sour asset
        icon.setBackgroundResource(R.drawable.flavor_icon_sour_milk);
        label.setText("Sour");
    }
}