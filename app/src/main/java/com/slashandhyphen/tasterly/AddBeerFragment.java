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


public class AddBeerFragment extends Fragment {
    String TAG = "AddBeerFragmentZAZZ";

    Button addBeerButton;
    Button seeBeerButton;
    EditText beerNameText;
    BeerView mBeerView;

    RelativeLayout rl;
    Resources res;

    FlavorButtonHandler mFlavorButtonHandler;
    ButtonHandler mButtonHandler;
    LayoutListener mLayoutListener;
    Beer mBeer;
    BeerDB beerDB;

    float originX, originY;
    int rlHeight, rlWidth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declare Variables!
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);
        mButtonHandler = new ButtonHandler();
        mFlavorButtonHandler = new FlavorButtonHandler();
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

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

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

    class FlavorButtonHandler implements View.OnLongClickListener, View.OnClickListener {

        @Override
        public void onClick(View mButton) {

        }

        @Override
        public boolean onLongClick(View mButton) {
            Toast.makeText(getActivity(), "Button Really Clicked", Toast.LENGTH_SHORT).show();
            return true;
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
