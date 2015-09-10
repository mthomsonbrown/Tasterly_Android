package com.slashandhyphen.tasterly.FlavorViewStuff;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.slashandhyphen.tasterly.FlavorViewStuff.Flavors.BitterFlavorView;
import com.slashandhyphen.tasterly.FlavorViewStuff.Flavors.FloralFlavorView;
import com.slashandhyphen.tasterly.FlavorViewStuff.Flavors.FruityFlavorView;
import com.slashandhyphen.tasterly.FlavorViewStuff.Flavors.HopsFlavorView;
import com.slashandhyphen.tasterly.FlavorViewStuff.Flavors.SourFlavorView;

/**
 * Created by ookamijin on 6/26/2015.
 *
 * <P>
 *     This is a description of the view that manages the different flavor objects relating to beer.
 *     It sets up the hierarchy of flavors, and <I>should</I> choose what type of geometry to
 *     assign, manage the control button icon, click listener, etc...  This is all currently handled
 *     by OmNomView, so refactoring shouldn't be too difficult.
 * </P>
 */
public class BeerView extends OmNomView {
    static String TAG = "BeerView";

    /**
     * Programmatic constructor
     */
    public BeerView(Context context) {
        super(context);
    }

    /**
     * XML Constructor.  This is the default constructor for classes extending OmNomView.
     * It uses an origin view created by OmNomView to setup a hierarchy for the individual
     * FlavorView objects.
     *
     * @param context Reference to the calling activity's base layout
     * @param attrs XML attributes set for this instance of OmNomView
     */
    public BeerView(Context context, AttributeSet attrs) {
        super(context, attrs);

        originView.addChild(new HopsFlavorView(context));
        originView.addChild(new BitterFlavorView(context)); // TODO Change this to malt after i create a malt asset...
        originView.addChild(new FloralFlavorView(context));
        originView.addChild(new SourFlavorView(context));
        originView.addChild(new FruityFlavorView(context));

        for(FlavorView child : originView.getChildren()) {
            child.label.setText(child.label.getText().toString() +
                    originView.getChildren().indexOf(child));
        }

        addChildren(originView);

    }


    /**
     * Add Children adds all declared FlavorViews to the base OmNomView RelativeLayout
     *
     * @param originView The origin FlavorView containing a reference to all child views
     */
    private void addChildren(FlavorView originView) {
        for(View child : originView.getChildren()) {
            addView(child);
        }
    }

}
