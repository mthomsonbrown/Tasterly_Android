package com.slashandhyphen.tasterly.FlavorViewStuff.Flavors;

import android.content.Context;

import com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView;

/**
 * Created by ookamijin on 8/27/2015.
 *
 * Extension of FlavorView for adding flavor-specific assets and possibly behavior/functionality
 */
public class SweetFlavorView extends FlavorView {
    public SweetFlavorView(Context context) {
        super(context);
        label.setText("Sweet");
    }
}