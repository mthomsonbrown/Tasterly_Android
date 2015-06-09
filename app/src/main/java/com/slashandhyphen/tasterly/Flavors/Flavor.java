package com.slashandhyphen.tasterly.Flavors;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * This class contains all the information and views, buttons, etc. associated with each flavor.
 */
public abstract class Flavor {
    static String TAG = "FlavorObject";
    Context context;
    RelativeLayout pLayout, mLayout;
    FlavorButtonHandler mFlavorButtonHandler;

    public Flavor(Context context, RelativeLayout pLayout) {

        this.context = context;
        this.pLayout = pLayout;
        mFlavorButtonHandler = new FlavorButtonHandler();


        mLayout = new RelativeLayout(context);
        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        pLayout.addView(mLayout);

    }

    public abstract void showButton();

    class FlavorButtonHandler implements View.OnLongClickListener, View.OnClickListener {
        @Override
        public void onClick(View v) {
            Log.d(TAG, "Button Clicked...");
            Toast.makeText(context, "Touched a separate class", Toast.LENGTH_SHORT).show();
        }

        @Override
        public boolean onLongClick(View v) {
            return false;
        }
    }
}
