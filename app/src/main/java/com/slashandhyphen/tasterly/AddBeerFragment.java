package com.slashandhyphen.tasterly;

import android.content.res.Resources;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
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
    BeerView beerView;

    RelativeLayout rl;
    Resources res;

    ButtonHandler buttonHandler;
    Beer beer;
    BeerDB beerDB;

    /**
     * Creates references to the different elements in the AddBeerFragment, then attaches a
     * GlobalLayoutListener
     *
     * @param inflater Used to add the fragment XML to the calling activity
     * @param container The ViewGroup object that contains this fragment
     * @param savedInstanceState State of user input if any has been saved to the bundle
     * @return A representation of this fragment as a view for the calling activity to show
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Declare Variables!
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer, container, false);
        buttonHandler = new ButtonHandler();
        beer = new Beer();

        // Declare Database!
        beerDB = new BeerDB(getActivity());

        // Declare Non-Flavor Button Junk!
        addBeerButton = (Button) rl.findViewById(R.id.add_beer_test);
        addBeerButton.setOnClickListener(buttonHandler);
        seeBeerButton = (Button) rl.findViewById(R.id.see_beer_test);
        seeBeerButton.setOnClickListener((buttonHandler));
        beerNameText = (EditText) rl.findViewById(R.id.edit_text_test);

        // Declare Flavor Button Junk!
        beerView = (BeerView) rl.findViewById(R.id.beer_view);

        // Return all the junk!
        return rl;
    }

    /**
     * Handles click events of buttons controlled directly by the fragment
     */
    class ButtonHandler implements  View.OnClickListener {

        /**
         * Determines what button was clicked and responds appropriately
         *
         * @param mButton A reference to the view that was clicked
         */
        @Override
        public void onClick(View mButton) {
            if (mButton.getId() == addBeerButton.getId()) {

                // Add beer name to the beer object
                if (beerNameText.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(), "Need to enter a name",
                            Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    beer.setName(beerNameText.getText().toString());
                }

                // Add flavor data to the beer object
                beer.addFlavors(beerView.getFlavorHash());

                // Add beer to the DB
                beerDB.add(beer);
            }
            if (mButton.getId() == seeBeerButton.getId()) {
                Toast.makeText(getActivity(), beerDB.expose(), Toast.LENGTH_SHORT).show();
            }
        }
    }

}
