package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;



/**
 * Created by ookamijin on 3/21/2016.
 * Development class to support all functionality
 */
public class AddBeerAlphaFragment extends Fragment {

    RelativeLayout rl;
    Resources res;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer_alpha, container, false);



        return rl;
    }
}
