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
    FlavorView testFlavor;
    // Don't think this will ever be used...
    public OmNomView(Context context) {
        super(context);
        this.context = context;
    }

    public OmNomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        addFlavorViews();
    }

    private void addFlavorViews() {
        testFlavor = new FlavorView(context);
        testFlavor.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        testFlavor.setId(View.generateViewId());
        addView(testFlavor);
    }
}
