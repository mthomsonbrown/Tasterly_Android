package com.slashandhyphen.tasterly.FlavorControl.Flavors;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * This class contains all the information and views, buttons, etc. associated with each flavor.
 */
public abstract class Flavor {
    static String TAG = "FlavorObject";
    Context context;
    RelativeLayout pLayout, mLayout;
    FlavorButtonHandler mFlavorButtonHandler;
    Button flavorButton;

    public Flavor(Context context, RelativeLayout pLayout) {

        this.context = context;
        this.pLayout = pLayout;
        mFlavorButtonHandler = new FlavorButtonHandler();

        initializeLayout();
        initializeView();
    }

    protected void initializeLayout() {
        mLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
    }

    protected void initializeView() {
        flavorButton = new Button(context);
        flavorButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        flavorButton.setId(View.generateViewId());
        flavorButton.setText("DEFAULT FLAVOR TEXT");
        flavorButton.setOnClickListener(mFlavorButtonHandler);
    }

    public void showContent() {
        pLayout.addView(mLayout);
        mLayout.addView(flavorButton);
    }

    public int getHeight() {
        return mLayout.getHeight();
    }

    public void setX(float x) {
        mLayout.setX(x);
    }

    public void setY(float y) {
        mLayout.setY(y);
    }

    class FlavorButtonHandler implements View.OnLongClickListener, View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "Button Clicked...");
            Toast.makeText(context, "Touched a separate class", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
