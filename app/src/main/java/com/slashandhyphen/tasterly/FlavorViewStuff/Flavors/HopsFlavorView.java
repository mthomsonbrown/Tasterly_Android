package com.slashandhyphen.tasterly.FlavorViewStuff.Flavors;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView;
import com.slashandhyphen.tasterly.R;

/**
 * Created by ookamijin on 7/1/2015.
 *
 * Extension of FlavorView for adding flavor-specific assets and possibly behavior/functionality
 */
public class HopsFlavorView extends FlavorView {
    public HopsFlavorView(Context context) {
        super(context);
        icon.setBackgroundResource(R.drawable.flavor_icon_acidic);
        label.setText("Hops");
    }
}
