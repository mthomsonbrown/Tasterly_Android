package com.slashandhyphen.tasterly;

import android.os.Bundle;
import android.app.Fragment;
import android.text.Layout;
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

    // TODO Dynamasize
    Button testButtons[] = new Button[2];
    Button testButton;

    SeekBar mSeekBar;
    RelativeLayout rl;
    ButtonHandler mButtonHandler;

    String TAG = "AddBeerFragment";
    int origin[] = new int[2];
    int rlHeight, rlWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);
        mButtonHandler = new ButtonHandler();
        LayoutListener mLayoutListener = new LayoutListener();

        // TODO Iterate!
        testButtons[0] = (Button) rl.findViewById(R.id.test_button1);
        testButtons[0].setOnClickListener(mButtonHandler);
        testButtons[0].setOnLongClickListener(mButtonHandler);
        testButtons[1] = (Button) rl.findViewById(R.id.test_button2);
        testButtons[1].setOnClickListener(mButtonHandler);
        testButtons[1].setOnLongClickListener(mButtonHandler);

        //TODO Test button
        /* Apparently all views need to be instantiated here, and then their coordinates set in
         * the LayoutListener */
        testButton = new Button(getActivity());
        testButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        testButton.setText("Holy crap a button!!!");

        rl.addView(testButton);

        rl.setOnTouchListener(new mTouchListener());
        rl.getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);
        mSeekBar = (SeekBar) rl.findViewById((R.id.addBeerSeekBar));

        return rl;
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        //TODO Make this not be called n+1 for every screen change
        @Override
        public void onGlobalLayout() {
            rl.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            rlHeight = rl.getHeight();
            rlWidth = rl.getWidth();
            origin[0] = rlWidth / 2;
            origin[1] = rlHeight / 2;

            testButton.setX(origin[0] - testButton.getWidth() / 2 + origin[0] / 4 * 3);
            testButton.setY(origin[1] - testButton.getHeight() / 2);
            testButton.setOnClickListener(mButtonHandler);

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

        // TODO Dynamasize
        float testOriginX[] = new float[3];
        float testOriginY[] = new float[3];

        float touchOriginX, touchOriginY, dY, dX = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                // TODO Iterate!
                testOriginX[0] = testButtons[0].getX();
                testOriginY[0] = testButtons[0].getY();
                testOriginX[1] = testButtons[1].getX();
                testOriginY[1] = testButtons[1].getY();
                testOriginX[2] = testButton.getX();
                testOriginY[2] = testButton.getY();
                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                // TODO Iterate!
                testButtons[0].setX(testOriginX[0] - dX);
                testButtons[0].setY(testOriginY[0] - dY);
                testButtons[1].setX(testOriginX[1] - dX);
                testButtons[1].setY(testOriginY[1] - dY);
                testButton.setX(testOriginX[2] - dX);
                testButton.setY(testOriginY[2] - dY);
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                // TODO Maybe a better way to implement
                mSeekBar.setVisibility(View.GONE);
            }

            return false;
        }
    }

}
