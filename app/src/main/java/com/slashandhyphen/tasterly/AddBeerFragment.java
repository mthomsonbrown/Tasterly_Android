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
import com.slashandhyphen.tasterly.Models.Beer;


public class AddBeerFragment extends Fragment {

    Button beerButton;
    Button addBeerButton;
    Button seeBeerButton;
    EditText beerNameText;

    // Flavor Buttons
    Button flavorButtonsPrimary[];
    String flavorButtonNamesPrimary[];
    Button flavorButtonsWater[];
    String flavorButtonNamesWater[];

    SeekBar mSeekBar;
    RelativeLayout rl;
    Resources res;

    FlavorButtonHandler mFlavorButtonHandler;
    ButtonHandler mButtonHandler;
    LayoutListener mLayoutListener;
    Beer mBeer;
    BeerDB beerDB;

    String TAG = "AddBeerFragmentZAZZ";
    float originX, originY;
    float waterOriginX, waterOriginY;
    int rlHeight, rlWidth;
    int viewDiameter;

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
        flavorButtonNamesPrimary = res.getStringArray(R.array.primary_flavors);
        flavorButtonsPrimary = new Button[flavorButtonNamesPrimary.length];
        flavorButtonNamesWater = res.getStringArray((R.array.water_flavors));
        flavorButtonsWater = new Button[flavorButtonNamesWater.length];

        for (int i = 0; i < flavorButtonsPrimary.length; ++i) {
            flavorButtonsPrimary[i] = new Button(getActivity());
            flavorButtonsPrimary[i].setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            flavorButtonsPrimary[i].setId(View.generateViewId());

            // Add resources to flavor buttons
            flavorButtonsPrimary[i].setText(flavorButtonNamesPrimary[i]);
            flavorButtonsPrimary[i].setBackgroundResource(R.drawable.test_icon);
            //flavorButtonsPrimary[i].setBackgroundColor(getResources().getColor(R.color.primary_1));

            flavorButtonsPrimary[i].setOnClickListener(mFlavorButtonHandler);
            flavorButtonsPrimary[i].setOnLongClickListener(mFlavorButtonHandler);
            rl.addView(flavorButtonsPrimary[i]);
        }

        for (int i = 0; i < flavorButtonsWater.length; ++i) {
            flavorButtonsWater[i] = new Button(getActivity());
            flavorButtonsWater[i].setLayoutParams(new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT,
                    RelativeLayout.LayoutParams.WRAP_CONTENT));
            flavorButtonsWater[i].setId(View.generateViewId());

            // Add resources to flavor buttons
            flavorButtonsWater[i].setText(flavorButtonNamesWater[i]);
            flavorButtonsWater[i].setBackgroundResource(R.drawable.test_icon);
            //flavorButtonsWater[i].setBackgroundColor(getResources().getColor(R.color.primary_1));

            flavorButtonsWater[i].setOnClickListener(mFlavorButtonHandler);
            flavorButtonsWater[i].setOnLongClickListener(mFlavorButtonHandler);
            rl.addView(flavorButtonsWater[i]);

        }

        rl.setOnTouchListener(new mTouchListener());
        rl.getViewTreeObserver().addOnGlobalLayoutListener(mLayoutListener);

        mSeekBar = (SeekBar) rl.findViewById((R.id.addBeerSeekBar));

        return rl;
    }

    class LayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        @Override
        public void onGlobalLayout() {
            rl.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            int buttonRadius;
            double drawRadius;

            //Getting view measurements
            rlHeight = rl.getHeight();
            rlWidth = rl.getWidth();
            originX = rlWidth / 2;
            originY = rlHeight / 2;

            beerButton.setX(originX - beerButton.getWidth() / 2);
            beerButton.setY(originY - beerButton.getHeight() / 2);

            // Geomancy begins here.
            if(rlHeight < rlWidth)
                viewDiameter = rlHeight;
            else
                viewDiameter = rlWidth;

            // Setting max distance a button can be placed from origin, assuming all buttons
            // are the same size
            buttonRadius = viewDiameter / 2  - flavorButtonsPrimary[0].getHeight();

            drawRadius = buttonRadius / 1.5;

            // arrange in circle
            for(int i = 0; i < flavorButtonsPrimary.length; ++i) {
                double theta = ((2 * Math.PI) / flavorButtonsPrimary.length) * i;
                flavorButtonsPrimary[i].
                        setX((float)((originX + drawRadius * Math.cos(theta + Math.PI)) -
                                flavorButtonsPrimary[i].getHeight() / 2));
                flavorButtonsPrimary[i].
                        setY((float)((originY + drawRadius *
                                (float)Math.sin(theta + Math.PI)) - flavorButtonsPrimary[i].getHeight() / 2));
            }

            // Find water button... this is a terrible design...
            for (int i = 0; i < flavorButtonNamesPrimary.length; ++i) {
                if(flavorButtonsPrimary[i].getText().toString().equals("W")) {
                    waterOriginX = flavorButtonsPrimary[i].getX() + flavorButtonsPrimary[i].getWidth() - flavorButtonsPrimary[i].getWidth() / 2;
                    waterOriginY = flavorButtonsPrimary[i].getY() + flavorButtonsPrimary[i].getHeight() - flavorButtonsPrimary[i].getHeight() / 2;

                }
            }

            for(int i = 0; i < flavorButtonsWater.length; ++i) {
                double theta = ((2 * Math.PI) / flavorButtonsWater.length) * i;
                flavorButtonsWater[i].
                        setX((float)((waterOriginX + drawRadius / 2 * Math.cos(theta + Math.PI)) -
                                flavorButtonsWater[i].getHeight() / 2));
                flavorButtonsWater[i].
                        setY((float)((waterOriginY + drawRadius / 2 *
                                (float)Math.sin(theta + Math.PI)) - flavorButtonsWater[i].getHeight() / 2));
            }
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

    class mTouchListener implements View.OnTouchListener {

        float beerButtonOriginX, beerButtonOriginY;
        float testOriginX[] = new float[flavorButtonsPrimary.length];
        float testOriginY[] = new float[flavorButtonsPrimary.length];

        float touchOriginX, touchOriginY, dY, dX = 0;

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                beerButtonOriginX = beerButton.getX();
                beerButtonOriginY = beerButton.getY();
                for(int i = 0; i < flavorButtonsPrimary.length; ++i) {
                    testOriginX[i] = flavorButtonsPrimary[i].getX();
                    testOriginY[i] = flavorButtonsPrimary[i].getY();
                }

                touchOriginX = event.getRawX();
                touchOriginY = event.getRawY();
                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                dY = touchOriginY - event.getRawY();
                dX = touchOriginX - event.getRawX();

                beerButton.setX(beerButtonOriginX - dX);
                beerButton.setY(beerButtonOriginY - dY);
                for(int i = 0; i < flavorButtonsPrimary.length; ++i) {
                    flavorButtonsPrimary[i].setX(testOriginX[i] - dX);
                    flavorButtonsPrimary[i].setY(testOriginY[i] - dY);
                }

                return true;
            }

            if (event.getAction() == MotionEvent.ACTION_UP) {

                mSeekBar.setVisibility(View.GONE);
            }

            return false;
        }
    }

}
