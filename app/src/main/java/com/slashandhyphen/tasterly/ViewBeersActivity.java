package com.slashandhyphen.tasterly;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;


public class ViewBeersActivity extends ActionBarActivity {
    String TAG = "ViewBeersFragment";
    FragmentManager fm = getFragmentManager();
    Fragment fragment1 = fm.findFragmentById(R.id.fragment_view_beers);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_beers);

        // Make Activity fullscreen
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        FragmentTransaction ft = fm.beginTransaction();
        if (fragment1 == null) {
            ft.add(R.id.fragment_view_beers, new ViewBeersFragment());
        }

        ft.commit();
    }


}
