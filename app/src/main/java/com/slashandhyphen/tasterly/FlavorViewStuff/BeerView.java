package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

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

        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        addChildren(originView);
    }

    private void addChildren(FlavorView originView) {
        for(View child : originView.getChildren()) {
            addView(child);
        }
    }
}
