package com.slashandhyphen.tasterly;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class AddBeerFragment extends Fragment {
    Button testButtons[] = new Button[2];
    RelativeLayout rl;
    FragmentActivity fa;
    String TAG = "AddBeer";
    float origin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        fa = (FragmentActivity) super.getActivity();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);

        // TODO Make this not return 0.0
        origin = rl.getHeight();
        Toast.makeText(getActivity(), "RL Height is " + origin, Toast.LENGTH_SHORT).show();
        testButtons[0] = (Button) rl.findViewById(R.id.test_button1);
        testButtons[0].setOnClickListener(new mClickListener());
        testButtons[0].setOnTouchListener(new mTouchListener());
        testButtons[1] = (Button) rl.findViewById(R.id.test_button2);
        testButtons[1].setOnClickListener(new mClickListener());
        testButtons[1].setOnTouchListener(new mTouchListener());

        rl.setOnTouchListener(new mTouchListener());

        return rl;
    }

    class mClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Click Beer", Toast.LENGTH_SHORT).show();
        }
    }

    class mTouchListener implements View.OnTouchListener {
        float testOriginX[] = new float[2];
        float testOriginY[] = new float[2];
        float touchOriginX, touchOriginY = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                testOriginX[0] = testButtons[0].getX();
                testOriginY[0] = testButtons[0].getY();
                testOriginX[1] = testButtons[1].getX();
                testOriginY[1] = testButtons[1].getY();
                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();
            }
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                float dY = touchOriginY - event.getRawY();
                float dX = touchOriginX - event.getRawX();
                testButtons[0].setY(testOriginY[0] - dY);
                testButtons[0].setX(testOriginX[0] - dX);
                testButtons[1].setY(testOriginY[1] - dY);
                testButtons[1].setX(testOriginX[1] - dX);

            }

            return false;
        }
    }

}
