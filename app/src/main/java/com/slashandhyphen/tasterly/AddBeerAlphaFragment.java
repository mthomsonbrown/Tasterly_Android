package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;


/**
 * Created by ookamijin on 3/21/2016.
 * Development class to support all add beer functionality
 */
public class AddBeerAlphaFragment extends Fragment implements View.OnClickListener  {

    RelativeLayout rl;
    Resources res;
    ImageButton imageButtonPhoto;
    Button buttonFlavorWheel;

    String TAG = "++Alpha Add Beer Fragment++";
    Camera camera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer_alpha, container, false);
        imageButtonPhoto = (ImageButton) rl.findViewById(R.id.image_button_photo);
        imageButtonPhoto.setOnClickListener(this);
        buttonFlavorWheel = (Button) rl.findViewById(R.id.button_flavor_wheel);
        buttonFlavorWheel.setOnClickListener(this);
        camera = new Camera(getActivity());

        return rl;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_button_photo:
                camera.takePicture();
                break;
            case R.id.button_flavor_wheel:
                FlavorWheelAlphaFragment wheelFrag = new FlavorWheelAlphaFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.add_beer_fragment_container, wheelFrag);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.addToBackStack(null);
                ft.commit();
                break;
            default:
                break;
        }
    }
}
