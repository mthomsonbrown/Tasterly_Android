package com.slashandhyphen.tasterly.Flavors;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

/**
 * Created by ookamijin on 6/5/2015.
 */
public class FlavorFlav extends Flavor {

    public FlavorFlav(Context context, RelativeLayout pLayout) {
        super(context, pLayout);
    }

    public void showButton() {
        Button testButton = new Button(context);
        testButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        testButton.setId(View.generateViewId());
        testButton.setText("TEST FLAVOR BUTTON");
        testButton.setOnClickListener(mFlavorButtonHandler);
        mLayout.addView(testButton);
    }
}
