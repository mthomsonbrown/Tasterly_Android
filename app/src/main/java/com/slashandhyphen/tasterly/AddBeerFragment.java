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

    // TODO Dynamasize
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

        // TODO Iterate!
        testButtons[0] = (Button) rl.findViewById(R.id.test_button1);
        testButtons[0].setOnTouchListener(new mTouchListener());
        testButtons[1] = (Button) rl.findViewById(R.id.test_button2);
        testButtons[1].setOnTouchListener(new mTouchListener());

        rl.setOnTouchListener(new mTouchListener());

        return rl;
    }

    class mTouchListener implements View.OnTouchListener {

        // TODO Dynamasize
        float testOriginX[] = new float[2];
        float testOriginY[] = new float[2];

        float touchOriginX, touchOriginY, dY, dX = 0;

        // TODO Add to a config file...
        float clickThreshold = 5;

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

            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (dX < clickThreshold && dY < clickThreshold &&   // Handle Click Events
                        dX > -clickThreshold && dY > -clickThreshold &&
                        v.getId() != R.id.add_beer_layout) {


                    Toast.makeText(getActivity(), "Didn't move " + dY, Toast.LENGTH_SHORT).show();
                }
                return true;
            }

            return false;
        }
    }

}
