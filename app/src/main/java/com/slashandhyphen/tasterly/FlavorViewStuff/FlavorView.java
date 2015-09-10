package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slashandhyphen.tasterly.R;

import java.util.ArrayList;

/**
 * Created by ookamijin on 6/26/2015.
 *
 * <P>
 *      This is the base class for flavor objects.  It contains many different view objects such as
 *      icon and seek bar, that can be displayed or hidden in different circumstances.  This
 *      causes the FlavorView to expand and contract its dimensions, requiring geometry to be
 *      redrawn.
 * </P>
 */
public class FlavorView extends RelativeLayout {
    protected TextView label;
    protected ImageView icon;
    private SeekBar mSeekBar;
    private ImageView mExpandButton;
    private OmNomView parent;
    private float savedX, savedY;

    private ArrayList<FlavorView> children;
    private FlavorClickListener mClickListener;
    private Context context;

    static final int SEEK_BAR_LENGTH = 350;

    /**
     * This constructor creates all of the different view elements and adds them to the base
     * RelativeLayout.  The order that they're added is important in that their positioning
     * relates to one another.  This has been an overall fragile approach, and creating a
     * FlavorView XML description would probably be a much better way of handling these assets.
     *
     * @param context reference to the calling application/activity
     */
    public FlavorView(Context context) {
        super(context);
        this.context = context;

        //  Create Self
        setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        setId(View.generateViewId());
        mClickListener = new FlavorClickListener();
        setOnClickListener(mClickListener);
        //setBackgroundColor(getResources().getColor(R.color.complement_1));

        // Create Label
        label = new TextView(context);
        label.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        label.setId(View.generateViewId());
        label.setText("DEFAULT LABEL TEXT FOR SHAME!!!!");
        label.setVisibility(GONE);
        addView(label);

        // Create Expand Button
        mExpandButton = new ImageView(context);
        RelativeLayout.LayoutParams expandParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        mExpandButton.setLayoutParams(expandParams);
        mExpandButton.setId(View.generateViewId());
        mExpandButton.setBackgroundResource(R.drawable.expand_icon);
        mExpandButton.setVisibility(GONE);
        addView(mExpandButton);

        // Create Icon
        icon = new ImageView(context);
        RelativeLayout.LayoutParams iconParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        iconParams.addRule(RelativeLayout.BELOW, mExpandButton.getId());
        icon.setLayoutParams(iconParams);
        icon.setId(View.generateViewId());
        icon.setBackgroundResource(R.drawable.test_icon);
        addView(icon);

        // Create Seek Bar
        mSeekBar = new SeekBar(context);
        RelativeLayout.LayoutParams seekParams =
                new RelativeLayout.LayoutParams(SEEK_BAR_LENGTH, LayoutParams.WRAP_CONTENT);
        seekParams.addRule(RelativeLayout.RIGHT_OF, icon.getId());
        seekParams.addRule(RelativeLayout.BELOW, mExpandButton.getId());
        mSeekBar.setLayoutParams(seekParams);
        mSeekBar.setId(View.generateViewId());
        mSeekBar.setVisibility(GONE);
        addView(mSeekBar);

        // Set expand button params based on new seekbar...
        expandParams.addRule(RelativeLayout.RIGHT_OF, mSeekBar.getId());
        mExpandButton.setLayoutParams(expandParams);

        // This is the "linked list" part of FlavorView.  Each FlavorView can be considered the
        // node for another array of FlavorViews.
        children = new ArrayList<>();
    }

    /**
     * This is called by the managing OmNomView to create a hierarchy of flavors
     *
     * @param view The FlavorView to add to the list
     */
    public void addChild(FlavorView view) {
        children.add(view);
    }

    /**
     * Returns an array of FlavorViews that this view is the parent of.
     *
     * @return an array of FlavorViews that this view is the parent of.
     */
    public ArrayList<FlavorView> getChildren() {
        return children;
    }

    /**
     * Handles click events on FlavorView objects.
     */
    class FlavorClickListener implements OnClickListener {

        /**
         * Default behavior when a FlavorView has been clicked.  This can be overridden by the
         * views that extend FlavorView.
         *
         * @param v In this implementation, v is a reference to self, so not useful.
         */
        @Override
        public void onClick(View v) {
            // TODO should be called in a different listener
            parent = (OmNomView) getParent();

            Toast.makeText(context, "Clicked a view: " + getId(), Toast.LENGTH_SHORT).show();

            mSeekBar.setVisibility(VISIBLE);
            mExpandButton.setVisibility(VISIBLE);
            parent.setBackgroundColor(getResources().getColor(R.color.primary_2));
            getViewTreeObserver().addOnGlobalLayoutListener(parent.mLayoutListener);

        }
    }

    /**
     * Sets a temporary X and Y value that can be changed by the
     * {@link com.slashandhyphen.tasterly.FlavorViewStuff.FlavorView#moveCoords(float, float)}
     * function.
     */
    public void saveCoords() {
        savedX = getX();
        savedY = getY();
    }

    /**
     * Moves this FlavorView by the amount supplied in the arguments.
     *
     * @param dX Delta X
     * @param dY Delta Y
     */
    public void moveCoords(float dX, float dY) {
        setX(savedX - dX);
        setY(savedY - dY);
    }

}
