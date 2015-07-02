package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.RelativeLayout;

import com.slashandhyphen.tasterly.R;


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
        addView(originView);
    }


    public float getOriginX() {
        return originView.getX();
    }

    public float getOriginY() {
        return originView.getY();
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            buildFlavorTree();

            Log.d(TAG, "Origin X is " + getOriginX());
            Log.d(TAG, "Origin Y is " + getOriginY());
        }
    }

    public void buildFlavorTree() {
        originView.setY(this.getHeight() / 2);
        originView.setX(this.getWidth() / 2);
        Log.d(TAG, "Origin height unt width are: " + originView.getHeight() + " & " + originView.getWidth());

        inflateChildren();

        OneRingGeomancy circleMaker = new OneRingGeomancy(originView, this);
        circleMaker.setDimensions();

    }

    public void inflateChildren() {

        for(View child : originView.getChildren()) {
            child.setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            child.setId(View.generateViewId());
            child.setBackgroundResource(R.drawable.test_icon);
            addView(child);
        }
    }
}
