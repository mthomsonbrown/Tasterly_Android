package com.slashandhyphen.tasterly;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Database.BeerDB;
import com.slashandhyphen.tasterly.FlavorViewStuff.BeerView;
import com.slashandhyphen.tasterly.Models.Beer;

/**
 * Created by ookamijin on 3/8/2015
 *
 * This fragment displays the {@link com.slashandhyphen.tasterly.FlavorViewStuff.BeerView},
 * interacts with the {@link com.slashandhyphen.tasterly.Database.BeerDB} database, and provides
 * some extra buttons for naming the beer, eventually taking a picture, extra notes, and for testing
 * at least will contain a save and sync button.
 */
public class AddBeerFragment extends Fragment {
    String TAG = "AddBeerFragmentZAZZ";

    Button addBeerButton;
    Button seeBeerButton;
    EditText beerNameText;
    BeerView mBeerView;

    RelativeLayout rl;
    Resources res;

    ButtonHandler mButtonHandler;
    LayoutListener mLayoutListener;
    Beer mBeer;
    BeerDB beerDB;

    float originX, originY;
    int rlHeight, rlWidth;

    /**
     * Creates references to the different elements in the AddBeerFragment, then attaches a
     * GlobalLayoutListener
     *
     * @param inflater Used to add the fragment XML to the calling activity
     * @param container The ViewGroup object that contains this fragment
     * @param savedInstanceState State of user input if any has been added to the bundle
     * @return A representation of this fragment as a view for the calling activity to show
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declare Variables!
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);
        mButtonHandler = new ButtonHandler();
        mLayoutListener = new LayoutListener();
        mBeer = new Beer();

        // Declare Database!
        beerDB = new BeerDB(getActivity());

        // Declare Non-Flavor Button Junk!
        addBeerButton = (Button) rl.findViewById(R.id.add_beer_test);
        addBeerButton.setOnClickListener(mButtonHandler);
        seeBeerButton = (Button) rl.findViewById(R.id.see_beer_test);
        seeBeerButton.setOnClickListener((mButtonHandler));
        beerNameText = (EditText) rl.findViewById(R.id.edit_text_test);

        // Declare Flavor Button Junk!
        mBeerView = (BeerView) rl.findViewById(R.id.beer_view);
       // mBeerView.setOnTouchListener(mTouchListener);

        rl.getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

        // Return all the junk!
        return rl;
    }


    /**
     * This is called when the layout dimensions are set, and is the first opportunity to set and
     * get dimension information from views that were not defined previously i.e. in XML
     */
    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        /**
         * This is the main function of interest to set and get dimensions of views created outside
         * of XML when they are available, as well as the dimensions of the fragment, as that is
         * inflated at runtime.
         */
        @Override
        public void onGlobalLayout() {
            rl.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            // Getting view measurements (for objects that are not "flavors")
            rlHeight = rl.getHeight();
            rlWidth = rl.getWidth();
            originX = rlWidth / 2;
            originY = rlHeight / 2;

        }
    }

    class ButtonHandler implements  View.OnClickListener {
        @Override
        public void onClick(View mButton) {
            if(mButton.getId() == addBeerButton.getId()) {
                if(beerNameText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Need to enter a name",
                            Toast.LENGTH_SHORT).show();
                }
                else {
                    mBeer.setName(beerNameText.getText().toString());

                    beerDB.add(mBeer);
                }
            }
            if(mButton.getId() == seeBeerButton.getId()) {
                Toast.makeText(getActivity(), beerDB.expose(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
