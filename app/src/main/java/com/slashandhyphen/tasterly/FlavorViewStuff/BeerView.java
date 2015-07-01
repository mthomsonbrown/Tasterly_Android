package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;

/**
 * Created by ookamijin on 6/26/2015.
 */
public class BeerView extends OmNomView {
    static String TAG = "BeerView";

    //Programmatic constructor
    public BeerView(Context context) {
        super(context);


    }

    //XML constructor
    public BeerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void buildFlavorTree() {
        super.buildFlavorTree();

    }
}
