package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/26/2015.
 */
public class OmNomView extends RelativeLayout {
    Context context;
    FlavorView originView;

    //Programmatic constructor
    public OmNomView(Context context) {
        super(context);
        this.context = context;
    }

    //XML constructor
    public OmNomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }
    public void buildFlavorTree() {
        addOriginView();
    }

    private void addOriginView() {
        originView = new FlavorView(context);

        originView.setId(View.generateViewId());
        originView.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        addView(originView);
        originView.setY(this.getHeight() / 2);
        originView.setX(this.getWidth() / 2);
    }

    public float getOriginX() {
        return originView.getX();
    }

    public float getOriginY() {
        return originView.getY();
    }
}
