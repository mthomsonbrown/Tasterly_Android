package com.slashandhyphen.tasterly;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Database.BeerDB;
import com.slashandhyphen.tasterly.FlavorControl.BeerFlavorHandler;
import com.slashandhyphen.tasterly.FlavorControl.FlavorHandler;
import com.slashandhyphen.tasterly.Models.Beer;


public class AddBeerFragment extends Fragment {

    Button beerButton;
    Button addBeerButton;
    Button seeBeerButton;
    EditText beerNameText;

    SeekBar mSeekBar;
    RelativeLayout rl;
    Resources res;

    FlavorButtonHandler mFlavorButtonHandler;
    ButtonHandler mButtonHandler;
    LayoutListener mLayoutListener;
    TouchListener mTouchListener;
    Beer mBeer;
    BeerDB beerDB;

    String TAG = "AddBeerFragmentZAZZ";
    float originX, originY;
    int rlHeight, rlWidth;
    int viewDiameter;

    //FlavorHandler flavors;

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
        beerButton = new Button(getActivity());
        beerButton.setLayoutParams(new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT));
        beerButton.setId(View.generateViewId());
        beerButton.setBackgroundResource(R.drawable.beer_icon);
        beerButton.setOnClickListener(mButtonHandler);
        rl.addView(beerButton);

        addBeerButton = (Button) rl.findViewById(R.id.add_beer_test);
        addBeerButton.setOnClickListener(mButtonHandler);
        seeBeerButton = (Button) rl.findViewById(R.id.see_beer_test);
        seeBeerButton.setOnClickListener((mButtonHandler));
        beerNameText = (EditText) rl.findViewById(R.id.edit_text_test);

        // Declare Flavor Button Junk!
        //flavors = new BeerFlavorHandler(getActivity(), rl);

        mTouchListener = new TouchListener();
        rl.setOnTouchListener(mTouchListener);
        rl.getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

        mSeekBar = (SeekBar) rl.findViewById((R.id.addBeerSeekBar));

        return rl;
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            rl.getViewTreeObserver().removeOnGlobalLayoutListener(this);

            //flavors.showContent();

            //Getting view measurements (for objects that are not "flavors")
            rlHeight = rl.getHeight();
            rlWidth = rl.getWidth();
            originX = rlWidth / 2;
            originY = rlHeight / 2;

            beerButton.setX(originX - beerButton.getWidth() / 2);
            beerButton.setY(originY - beerButton.getHeight() / 2);

        }
    }

    class FlavorButtonHandler implements View.OnLongClickListener, View.OnClickListener {

        int initialSeekProgress = 0;

        @Override
        public void onClick(View mButton) {
            SeekBarListener mListener = new SeekBarListener(mButton);
            mSeekBar.setOnSeekBarChangeListener(mListener);
            mSeekBar.setProgress(initialSeekProgress);
            mSeekBar.setX(mButton.getX() + mButton.getWidth());
            mSeekBar.setY(mButton.getY() + mButton.getHeight() / 3);
            mSeekBar.setVisibility(View.VISIBLE);
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
            if(mButton.getId() == beerButton.getId()) {
                Toast.makeText(getActivity(), "You've added Rainier...hope you're happy",
                        Toast.LENGTH_SHORT).show();
                mBeer.setName("Rainier");
            }
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

    class SeekBarListener implements SeekBar.OnSeekBarChangeListener {

        View v;

        SeekBarListener(View v) {
            this.v = v;
        }

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            Log.d(TAG, "Seek Bar Changed to " + seekBar.getProgress());
            Log.d(TAG, "View was " + v.getId());

            Button button = (Button) v;
            mBeer.addFlavor(button.getText().toString(), seekBar.getProgress());
            Log.d(TAG, "HASHMAP: " + mBeer.listFlavors());
        }
    }

    class TouchListener implements View.OnTouchListener {

        float beerButtonOriginX, beerButtonOriginY;

        float touchOriginX, touchOriginY, dY, dX = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                beerButtonOriginX = beerButton.getX();
                beerButtonOriginY = beerButton.getY();

                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();

                //flavors.saveOrigin();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {

                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                beerButton.setX(beerButtonOriginX - dX);
                beerButton.setY(beerButtonOriginY - dY);

                //flavors.moveOrigin(dX, dY);
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                mSeekBar.setVisibility(View.GONE);
            }

            return false;
        }
    }

}
