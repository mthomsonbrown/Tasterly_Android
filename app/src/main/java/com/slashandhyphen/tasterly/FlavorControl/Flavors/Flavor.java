package com.slashandhyphen.tasterly.FlavorControl.Flavors;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
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
        flavorButton.setText("DEFAULT TEXT!");
        flavorButton.setBackgroundResource(R.drawable.test_icon);

        initializeFlavorButton();
    }

    public abstract void initializeFlavorButton();

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
            Button mView = (Button) v;
            Log.d(TAG, "In a thing i knew i was in...");
            Toast.makeText(context, "View is " + mView.getText().toString(),
                    Toast.LENGTH_SHORT).show();

            // TODO this isn't working very well.  should maybe add seekbar to an xml file and load it programmatically?  issues with over nesting layouts?
            SeekBar mSeekBar = new SeekBar(context);
            mSeekBar.setMax(100);
            mSeekBar.setProgress(1);
            mSeekBar.setX(flavorButton.getX() + flavorButton.getWidth());
            mSeekBar.setY(flavorButton.getY() + flavorButton.getHeight() / 2 - mSeekBar.getHeight() / 2);
            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(300, 50);
            mSeekBar.setLayoutParams(lp);
            mSeekBar.setVisibility(View.VISIBLE);
            mLayout.addView(mSeekBar);

        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }


}
