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
 *
 * <P>
 *     OmNomView is a base class used to create geometric relationships between different
 *     {@link com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView} objects.
 * </P>
 */
// TODO This class handles way too much.  OriginView's coordinates should be set by geomancy.
// TODO The icon used for the origin should be set by the child class, and its implementation
// TODO should be decided by the child class as well...
public class OmNomView extends RelativeLayout {
    static String TAG = "OmDomView";
    Context context;
    FlavorView originView;
    Button controlButton;
    LayoutListener mLayoutListener;

    /**
     * Programmatic constructor
     */
    public OmNomView(Context context) {
        super(context);
        this.context = context;
    }

    /**
     * XML Constructor.  This is the default constructor of OmNomView.  It creates an origin that
     * subclasses can use as a starting point to add a flavorView hierarchy.
     *
     * @param context Reference to the calling activity's base layout
     * @param attrs XML attributes set for this instance of OmNomView
     */
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

    /**
     * When the layout is constructed, this class sets the coordinates of the views it controls
     */
    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        /**
         * This is the only method of the LayoutListener class, and sets the coordinates
         * for the control button.  It also calls Geomancy to set other things using
         * {@link com.slashandhyphen.tasterly.FlavorViewStuff.OneRingGeomancy}...
         */
        @Override
        public void onGlobalLayout() {
            getViewTreeObserver().removeOnGlobalLayoutListener(this);
            Log.d(TAG, "In global layout listener");
            buildFlavorTree();

            controlButton.setX(originView.getX() - controlButton.getWidth() / 2);
            controlButton.setY(originView.getY() - controlButton.getHeight() / 2);
        }
    }

    /**
     * Sets a central coordinate for the originView so that other views can reference the origin
     * as an independent point rather than the corner of an expanded view.  It then creates a
     * Geomancy object and the setDimensions function of that object:
     * {@link OneRingGeomancy#setDimensions()}
     */
    public void buildFlavorTree() {
        originView.setY(this.getHeight() / 2);
        originView.setX(this.getWidth() / 2);

        OneRingGeomancy circleMaker = new OneRingGeomancy(originView, this);
        circleMaker.setDimensions();
    }

    /**
     * Tells all FlavorView objects managed by this instance of OmNomView to set their x and y
     * coordinates.
     */
    public void saveCoords() {
        for(FlavorView child : originView.getChildren()) {
            child.saveCoords();
        }
    }

    /**
     * Gives every FlavorView object a value to change their coordinates for movement.
     *
     * @param dX Amount to move in the X axis
     * @param dY Amount to move in the Y axis
     */
    public void moveCoords(float dX, float dY) {
        for(FlavorView child : originView.getChildren()) {
            child.moveCoords(dX, dY);
        }
    }

    /**
     * Responds to clicks on the control button.  Default behavior may be good, but this should
     * probably be overridden by children.
     */
    class ControlButtonHandler implements  View.OnClickListener {

        /**
         * Responds to clicks on the control button.
         *
         * @param mButton The control button, the only button attached to this listener
        */
        @Override
        public void onClick(View mButton) {
                Toast.makeText(context, "This will give you some options probably at some point, " +
                        "maybe the same as the yet to be created menu...",
                        Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Responds to touch events on the background RelativeLayout of OmNomView.  This calculates
     * the delta of swipes and sends that data to the FlavorViews managed by this OmNomView.
     */
    class TouchListener implements View.OnTouchListener {

        float touchOriginX, touchOriginY, dY, dX = 0;
        float controlX, controlY = 0;

        /**
         * Called when the background RelativeLayout is interacted with.
         *
         * @param v Reference to the RelativeLayout background of OmNomView.
         * @param event Touch event to interact with.
         * @return True I believe invalidates the specific touch action.
         */
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
