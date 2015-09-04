package com.slashandhyphen.tasterly.FlavorViewStuff.Flavors;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;

import com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView;
import com.slashandhyphen.tasterly.R;

/**
 * Created by ookamijin on 7/1/2015.
 */
public class HopsFlavorView extends FlavorView {
    public HopsFlavorView(Context context) {
        super(context);
        //setBackgroundResource(R.drawable.test_icon);
        label.setText("Hops");
    }
}
