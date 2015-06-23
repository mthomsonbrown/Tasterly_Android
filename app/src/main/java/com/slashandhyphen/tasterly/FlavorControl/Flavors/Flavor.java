package com.slashandhyphen.tasterly.FlavorControl.Flavors;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.slashandhyphen.tasterly.R;

/**
 * This class contains all the information and views, buttons, etc. associated with each flavor.
 */
public abstract class Flavor {
    static String TAG = "FlavorObject";
    Context context;
    RelativeLayout pLayout, mLayout;
    FlavorButtonHandler mFlavorButtonHandler;
    Button flavorButton;
    float savedX, savedY;

    public Flavor(Context context, RelativeLayout pLayout) {

        this.context = context;
        this.pLayout = pLayout;
        mFlavorButtonHandler = new FlavorButtonHandler();

        initializeLayout();
        initializeView();
    }

    protected void initializeLayout() {
        mLayout = new RelativeLayout(context);
    }

    protected void initializeView() {
        flavorButton = new Button(context);
        flavorButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        flavorButton.setId(View.generateViewId());
        flavorButton.setOnClickListener(mFlavorButtonHandler);

        // prototype values
        flavorButton.setText("D");
        flavorButton.setBackgroundResource(R.drawable.test_icon);
    }

    public void showContent() {
        pLayout.addView(mLayout);
        mLayout.addView(flavorButton);
    }

    public int getHeight() {
        return mLayout.getHeight();
    }

    public void setX(float value) {
        mLayout.setX(value);
    }

    public void setY(float value) {
        mLayout.setY(value);
    }

    public void saveOrigin() {
        savedX = mLayout.getX();
        savedY = mLayout.getY();
    }

    public void moveOrigin(float dX, float dY) {
        mLayout.setX(savedX - dX);
        mLayout.setY(savedY - dY);
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
