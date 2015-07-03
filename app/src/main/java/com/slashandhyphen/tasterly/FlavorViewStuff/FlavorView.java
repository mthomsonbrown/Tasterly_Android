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
    OmNomView parent;

    ArrayList<View> children;
    FlavorClickListener mClickListener;
    Context context;

    static final int SEEK_BAR_LENGTH = 560;

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
        setBackgroundColor(getResources().getColor(R.color.complement_1));

        // Create Icon
        icon = new ImageView(context);
        icon.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        icon.setId(View.generateViewId());
        icon.setBackgroundResource(R.drawable.test_icon);
        addView(icon);

        // Create Label
        label = new TextView(context);
        label.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        label.setId(View.generateViewId());
        label.setText("DEFAULT LABEL TEXT FOR SHAME!!!!");
        addView(label);

        // Create Seek Bar
        mSeekBar = new SeekBar(context);
        RelativeLayout.LayoutParams seekParams =
                new RelativeLayout.LayoutParams(SEEK_BAR_LENGTH, LayoutParams.WRAP_CONTENT);
        seekParams.addRule(RelativeLayout.RIGHT_OF, icon.getId());
        mSeekBar.setLayoutParams(seekParams);
        mSeekBar.setId(View.generateViewId());
        mSeekBar.setVisibility(GONE);
        addView(mSeekBar);

        children = new ArrayList<>();
    }

    public void addChild(View view) {
        children.add(view);
    }

    public ArrayList<View> getChildren() {
        return children;
    }

    class FlavorClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            // TODO should be called in a different listener
            parent = (OmNomView) getParent();

            Toast.makeText(context, "Clicked a view: " + getId(), Toast.LENGTH_SHORT).show();
            mSeekBar.setVisibility(VISIBLE);
            parent.setBackgroundColor(getResources().getColor(R.color.primary_2));

            // TODO Doesn't seem to get new dimensions immediately.  probably need to call redraw in a listener, possibly onMeasure or some such...
            parent.buildFlavorTree();
        }
    }
}
