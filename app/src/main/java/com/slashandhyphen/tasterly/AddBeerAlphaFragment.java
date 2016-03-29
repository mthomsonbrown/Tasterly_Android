package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;


/**
 * Created by ookamijin on 3/21/2016.
 * Development class to support all functionality
 */
public class AddBeerAlphaFragment extends Fragment implements View.OnClickListener  {

    RelativeLayout rl;
    Resources res;
    ImageButton imageButtonPhoto;
    String photoPath;
    String TAG = "++Alpha Add Beer Fragment++";
    Camera camera;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        res = getResources();
        rl = (RelativeLayout) inflater.inflate(R.layout.fragment_add_beer_alpha, container, false);
        imageButtonPhoto = (ImageButton) rl.findViewById(R.id.image_button_photo);
        imageButtonPhoto.setOnClickListener(this);
        camera = new Camera(getActivity());

        return rl;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_button_photo:
                camera.takePicture();
                break;
            default:
                break;
        }
    }
}
