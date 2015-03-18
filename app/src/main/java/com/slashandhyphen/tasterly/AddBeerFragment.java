package com.slashandhyphen.tasterly;

import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;


public class AddBeerFragment extends Fragment {
    Button testButton;
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
        testButton = (Button) rl.findViewById(R.id.test_button);
        testButton.setOnClickListener(new mClickListener());
        testButton.setOnTouchListener(new mTouchListener());
        testButton.setOnDragListener(new mDragListener());

        return rl;
    }

    class mClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(getActivity(), "Click Beer", Toast.LENGTH_SHORT).show();
        }
    }

    class mTouchListener implements View.OnTouchListener {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {

                // TODO Make not spastic
                testButton.setY(event.getY());
                testButton.setX(event.getX());

            }

            return false;
        }
    }
    class mDragListener implements View.OnDragListener {
        @Override
        public boolean onDrag(View v, DragEvent event) {
            Toast.makeText(getActivity(), "Drag Beer", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}
