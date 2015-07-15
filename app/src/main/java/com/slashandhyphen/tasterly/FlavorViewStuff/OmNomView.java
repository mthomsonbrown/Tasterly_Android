package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;


/**
 * Created by ookamijin on 6/26/2015.
 */
public class OmNomView extends RelativeLayout {
    static String TAG = "OmDomView";
    Context context;
    FlavorView originView;
    LayoutListener mLayoutListener;


    //Programmatic constructor
    public OmNomView(Context context) {
        super(context);
        this.context = context;
    }

    //XML constructor
    public OmNomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mLayoutListener = new LayoutListener();
        getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

        originView = new FlavorView(context);
        originView.setId(View.generateViewId());
        originView.setVisibility(INVISIBLE);
        addView(originView);
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Log.d(TAG, "In global layout listener");
            buildFlavorTree();
        }
    }

    public void buildFlavorTree() {
        originView.setY(this.getHeight() / 2);
        originView.setX(this.getWidth() / 2);

        OneRingGeomancy circleMaker = new OneRingGeomancy(originView, this);
        circleMaker.setDimensions();
    }

    public void saveCoords() {
        for(FlavorView child : originView.getChildren()) {
            child.saveCoords();
        }
    }

    public void moveCoords(float dX, float dY) {
        for(FlavorView child : originView.getChildren()) {
            child.moveCoords(dX, dY);
        }
    }
}
