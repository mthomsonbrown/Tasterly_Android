package com.slashandhyphen.tasterly;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;


public class AddBeerFragment extends Fragment {

    // TODO Probably dynamasize
    Button flavorButtons[];
    String flavorButtonNames[];

    SeekBar mSeekBar;
    RelativeLayout rl;
    Resources res;

    ButtonHandler mButtonHandler;
    LayoutListener mLayoutListener;

    String TAG = "AddBeerFragmentZAZZ";
    int originX, originY;
    int rlHeight, rlWidth;
    int viewDiameter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declare Variables!
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);
        mButtonHandler = new ButtonHandler();
        mLayoutListener = new LayoutListener();

        // Declare Button Junk!
        flavorButtonNames = res.getStringArray(R.array.primary_flavors);
        flavorButtons = new Button[flavorButtonNames.length];

        // Add button arrays to view
        for (int i = 0; i < flavorButtons.length; ++i) {
            flavorButtons[i] = new Button(getActivity());
            flavorButtons[i].setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            flavorButtons[i].setId(View.generateViewId());

            // TODO come up with better names, also pictures and other item-specific junk
            flavorButtons[i].setText(flavorButtonNames[i]);
            flavorButtons[i].setBackgroundResource(R.drawable.test_icon);

            flavorButtons[i].setOnClickListener(mButtonHandler);
            flavorButtons[i].setOnLongClickListener(mButtonHandler);
            rl.addView(flavorButtons[i]);
        }

        rl.setOnTouchListener(new mTouchListener());
        rl.getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

        // TODO Not sure what to do with this...
        mSeekBar = (SeekBar) rl.findViewById((R.id.addBeerSeekBar));

        return rl;
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        //TODO Make this not be called n+1 for every screen change
        @Override
        public void onGlobalLayout() {
            rl.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int buttonRadius;
            double drawRadius;

            //Getting view measurements
            rlHeight = rl.getHeight();
            rlWidth = rl.getWidth();
            originX = rlWidth / 2;
            originY = rlHeight / 2;

            if(rlHeight < rlWidth)
                viewDiameter = rlHeight;
            else
                viewDiameter = rlWidth;

            // Setting max distance a button can be placed from origin, assuming all buttons
            // are the same size
            buttonRadius = viewDiameter / 2  - flavorButtons[0].getHeight();

            // TODO Subjective subset of maximum, should be static in config file
            drawRadius = buttonRadius / 1.5;

            // arrange in circle
            for(int i = 0; i < flavorButtons.length; ++i) {
                double theta = ((2 * Math.PI) / flavorButtons.length) * i;
                flavorButtons[i].setX((float)((originX + drawRadius * Math.cos(theta + Math.PI)) - flavorButtons[i].getHeight() / 2));
                flavorButtons[i].setY((float)((originY + drawRadius * (float)Math.sin(theta + Math.PI)) - flavorButtons[i].getHeight() / 2));
            }

        }
    }

    class ButtonHandler implements View.OnLongClickListener, View.OnClickListener {

        //TODO Add to config
        int initialSeekProgress = 0;

        @Override
        public void onClick(View mButton) {
            SeekBarListener mListener = new SeekBarListener(mButton);
            mSeekBar.setOnSeekBarChangeListener(mListener);
            mSeekBar.setProgress(initialSeekProgress);
            mSeekBar.setX(mButton.getX() + mButton.getWidth());
            mSeekBar.setY(mButton.getY());
            mSeekBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean onLongClick(View mButton) {
            // TODO Make this produce detail view
            Toast.makeText(getActivity(), "Button Really Clicked", Toast.LENGTH_SHORT).show();
            return true;
        }
    }

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        View v;

        SeekBarListener(View v) {
            this.v = v;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "Seek Bar Changed to " + seekBar.getProgress());
            Log.d(TAG, "View was " + v.getId());
        }
    }

    class mTouchListener implements View.OnTouchListener {

        float testOriginX[] = new float[flavorButtons.length];
        float testOriginY[] = new float[flavorButtons.length];

        float touchOriginX, touchOriginY, dY, dX = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                for(int i = 0; i < flavorButtons.length; ++i) {
                    testOriginX[i] = flavorButtons[i].getX();
                    testOriginY[i] = flavorButtons[i].getY();
                }

                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                for(int i = 0; i < flavorButtons.length; ++i) {
                    flavorButtons[i].setX(testOriginX[i] - dX);
                    flavorButtons[i].setY(testOriginY[i] - dY);
                }

                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                // TODO Maybe a better way to implement...yeah, probably put this in ACTION_MOVE
                mSeekBar.setVisibility(View.GONE);
            }

            return false;
        }
    }

}
