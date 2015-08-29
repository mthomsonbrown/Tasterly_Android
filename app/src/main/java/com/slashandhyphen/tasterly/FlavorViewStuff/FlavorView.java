package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slashandhyphen.tasterly.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ookamijin on 6/26/2015.
 */
public class FlavorView extends RelativeLayout {
    TextView label;
    SeekBar mSeekBar;
    ImageView icon;
    ImageView mExpandButton;
    OmNomView parent;
    float savedX, savedY;

    ArrayList<FlavorView> children;
    FlavorClickListener mClickListener;
    Context context;

    static final int SEEK_BAR_LENGTH = 350;

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
        //label.setVisibility(INVISIBLE);
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

        // What does this do?
        children = new ArrayList<>();
    }

    public void addChild(FlavorView view) {
        children.add(view);
    }

    public ArrayList<FlavorView> getChildren() {
        return children;
    }

    class FlavorClickListener implements OnClickListener {

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

    public void saveCoords() {
        savedX = getX();
        savedY = getY();
    }

    public void moveCoords(float dX, float dY) {
        setX(savedX - dX);
        setY(savedY - dY);
    }

}
