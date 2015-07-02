package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;

import com.slashandhyphen.tasterly.R;

/**
 * Created by ookamijin on 7/1/2015.
 */
public class HopsFlavorView extends FlavorView {
    public HopsFlavorView(Context context) {
        super(context);
        label.setText("Hops");
        setBackgroundResource(R.drawable.test_icon);
    }
}
