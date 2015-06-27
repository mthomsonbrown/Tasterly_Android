package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/26/2015.
 */
public class FlavorView extends RelativeLayout {

    Button testButton;
    public FlavorView(Context context) {
        super(context);
        testButton = new Button(context);

        testButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        testButton.setId(View.generateViewId());
        testButton.setText("Flavor View Button");
        addView(testButton);
    }
}
