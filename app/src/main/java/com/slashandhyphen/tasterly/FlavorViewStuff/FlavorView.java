package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ookamijin on 6/26/2015.
 */
public class FlavorView extends RelativeLayout {
    ArrayList<View> children;
    TextView label;

    public FlavorView(Context context) {
        super(context);
        label = new TextView(context);
        label.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        label.setId(View.generateViewId());
        addView(label);

        children = new ArrayList<>();
    }

    public void addChild(View view) {
        children.add(view);
    }

    // Sort of works...
    public ArrayList<View> getChildren() {
        return children;
    }
}
