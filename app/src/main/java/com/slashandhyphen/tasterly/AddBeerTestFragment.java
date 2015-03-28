package com.slashandhyphen.tasterly;

import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Models.Beer;


public class AddBeerTestFragment extends Fragment {

    Button saveBeerButton;
    TextView trialView;
    SeekBar mSeekBar;

    LinearLayout ll;
    FragmentActivity fa;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fa = (FragmentActivity) super.getActivity();
        ll = (LinearLayout) inflater.inflate(R.layout.fragment_add_beer_test, container, false);
        mSeekBar = (SeekBar) ll.findViewById(R.id.seekBar);
        trialView = (TextView) ll.findViewById(R.id.trialContent);
        saveBeerButton = (Button) ll.findViewById(R.id.saveBeerButton);

        saveBeerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                saveBeer(saveBeerButton);
            }
        });

        return ll;
    }

    private void saveBeer(View saveBeerButton) {
        Beer mBeer = new Beer();
        mBeer.setName(trialView.getText().toString());
       // mBeer.setGoodness((long)mSeekBar.getProgress());
        mBeer.save();

        Beer nBeer = Beer.findById(Beer.class, (long)1);

        Toast.makeText(getActivity(),
                "Beer name is " + nBeer.getName(), Toast.LENGTH_SHORT).show();

       // Toast.makeText(getActivity(),
              //  "Goodness is " + nBeer.getGoodness(), Toast.LENGTH_SHORT).show();

    }

}
