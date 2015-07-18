package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.slashandhyphen.tasterly.R;


/**
 * Created by ookamijin on 6/26/2015.
 */
public class OmNomView extends RelativeLayout {
    static String TAG = "OmDomView";
    Context context;
    FlavorView originView;
    Button controlButton;
    LayoutListener mLayoutListener;

    //Programmatic constructor
    public OmNomView(Context context) {
        super(context);
        this.context = context;
    }

    //XML constructor
    public OmNomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        mLayoutListener = new LayoutListener();
        originView = new FlavorView(context);
        controlButton = new Button(context);

        getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);
        setOnTouchListener(new TouchListener());
        originView.setId(View.generateViewId());
        originView.setVisibility(INVISIBLE);

        controlButton.setBackgroundResource(R.drawable.beer_icon);
        controlButton.setLayoutParams(new LayoutParams(
                LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT));
        controlButton.setId(View.generateViewId());
        controlButton.setOnClickListener(new ControlButtonHandler());

        addView(originView);
        addView(controlButton);
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Log.d(TAG, "In global layout listener");
            buildFlavorTree();

            controlButton.setX(originView.getX() - controlButton.getWidth() / 2);
            controlButton.setY(originView.getY() - controlButton.getHeight() / 2);
        }
    }

    public void buildFlavorTree() {
        originView.setY(this.getHeight() / 2);
        originView.setX(this.getWidth() / 2);

        OneRingGeomancy circleMaker = new OneRingGeomancy(originView, this);
        circleMaker.setDimensions();
    }

    public void saveCoords() {
        for(FlavorView child : originView.getChildren()) {
            child.saveCoords();
        }
    }

    public void moveCoords(float dX, float dY) {
        for(FlavorView child : originView.getChildren()) {
            child.moveCoords(dX, dY);
        }
    }

    class ControlButtonHandler implements  View.OnClickListener {
        @Override
        public void onClick(View mButton) {
                Toast.makeText(context, "This will give you some options probably at some point, " +
                        "maybe the same as the yet to be created menu...",
                        Toast.LENGTH_SHORT).show();
        }
    }

    class TouchListener implements View.OnTouchListener {

        float touchOriginX, touchOriginY, dY, dX = 0;
        float controlX, controlY = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();

                saveCoords();

                // should be held somewhere else
                controlX = controlButton.getX();
                controlY = controlButton.getY();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {

                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                controlButton.setX(controlX - dX);
                controlButton.setY(controlY - dY);

                moveCoords(dX, dY);
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {
                Log.d(TAG, "In ACTION_UP...I'll probably need this at some point");
            }

            return false;
        }
    }
}
