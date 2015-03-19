package com.slashandhyphen.tasterly;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;


public class AddBeerFragment extends Fragment {

    // TODO Dynamasize
    Button testButtons[] = new Button[2];
    SeekBar mSeekBar;

    RelativeLayout rl;
    FragmentActivity fa;
    String TAG = "AddBeerFragment";
    float origin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fa = (FragmentActivity) super.getActivity();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);
        ButtonHandler mButtonHandler = new ButtonHandler();

        // TODO Make this not return 0.0
        origin = rl.getHeight();
        Log.d(TAG, "RL Height is " + origin);

        // TODO Iterate!
        testButtons[0] = (Button) rl.findViewById(R.id.test_button1);
        testButtons[0].setOnClickListener(mButtonHandler);
        testButtons[0].setOnLongClickListener(mButtonHandler);
        testButtons[1] = (Button) rl.findViewById(R.id.test_button2);
        testButtons[1].setOnClickListener(mButtonHandler);
        testButtons[1].setOnLongClickListener(mButtonHandler);

        rl.setOnTouchListener(new mTouchListener());
        mSeekBar = (SeekBar) rl.findViewById((R.id.addBeerSeekBar));

        return rl;
    }

    class ButtonHandler implements View.OnLongClickListener, View.OnClickListener {

        //TODO Add to config
        int initialSeekProgress = 0;

        @Override
        public void onClick(View v) {
            // TODO Make this produce detail view
            Toast.makeText(getActivity(), "Button Clicked", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View mButton) {
            SeekBarListener mListener = new SeekBarListener(mButton);
            mSeekBar.setOnSeekBarChangeListener(mListener);
            mSeekBar.setProgress(initialSeekProgress);
            mSeekBar.setX(mButton.getX() + mButton.getWidth());
            mSeekBar.setY(mButton.getY());
            mSeekBar.setVisibility(View.VISIBLE);
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
        float testOriginX[] = new float[2];
        float testOriginY[] = new float[2];

        float touchOriginX, touchOriginY, dY, dX = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                // TODO Iterate!
                testOriginX[0] = testButtons[0].getX();
                testOriginY[0] = testButtons[0].getY();
                testOriginX[1] = testButtons[1].getX();
                testOriginY[1] = testButtons[1].getY();
                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                // TODO Iterate!
                testButtons[0].setY(testOriginY[0] - dY);
                testButtons[0].setX(testOriginX[0] - dX);
                testButtons[1].setY(testOriginY[1] - dY);
                testButtons[1].setX(testOriginX[1] - dX);
                return true;
            }

            return false;
        }
    }

}
