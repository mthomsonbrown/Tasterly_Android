package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by ookamijin on 6/26/2015.
 */
public class BeerView extends OmNomView {
    static String TAG = "BeerView";

    //Programmatic constructor
    public BeerView(Context context) {
        super(context);
    }

    //XML constructor
    public BeerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new HopsFlavorView(context));
        for(FlavorView child : originView.getChildren()) {
            child.label.setText(child.label.getText().toString() + originView.getChildren().indexOf(child));
        }

        addChildren(originView);

        this.setOnTouchListener(new FlavorTouchListener());
    }

    private void addChildren(FlavorView originView) {
        for(View child : originView.getChildren()) {
            addView(child);
        }
    }

    class FlavorTouchListener implements OnTouchListener {
        float touchOriginX, touchOriginY, dY, dX = 0;
        float childOrigin[][] = new float[originView.getChildren().size()][2];

        @Override
        public boolean onTouch(View v, MotionEvent event) {

            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();
                for (int i = 0; i < originView.getChildren().size(); ++i) {
                    childOrigin[i][0] = originView.getChildren().get(i).getX();
                    childOrigin[i][1] = originView.getChildren().get(i).getY();
                }
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {

                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                for(int i = 0; i < originView.getChildren().size(); ++i) {
                    originView.getChildren().get(i).setX(childOrigin[i][0] - dX);
                    originView.getChildren().get(i).setY(childOrigin[i][1] - dY);
                }
                return true;
            }


            return false;
        }

    }
}
