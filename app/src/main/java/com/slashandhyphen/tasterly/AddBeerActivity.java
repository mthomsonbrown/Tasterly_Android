package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class AddBeerActivity extends Activity {
    String TAG = "++AddBeerActivity++";
    FragmentManager fm = getFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.add_beer_fragment_container);
    //Fragment fragment2 = fm.findFragmentById(R.id.fragment_content_2);
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

        // Make Activity fullscreen
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        // Here's where I get the right fragment ID from sharedPreferences...
        preferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        int addBeerFragmentId = preferences.getInt("AddBeerFragment", -1);

        if (fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            switch (addBeerFragmentId) {
                case R.id.fragment_add_beer:
                    Log.d(TAG, "Trying to add normal fragment");
                    ft.add(R.id.add_beer_fragment_container, new AddBeerFragment());
                    break;
                case R.id.fragment_add_beer_alpha:
                    Log.d(TAG, "Trying to add alpha fragment");
                    ft.add(R.id.add_beer_fragment_container, new AddBeerFragmentAlpha());
                    break;
                default:
                    Log.d(TAG, "Trying to add default fragment in default");
                    ft.add(R.id.add_beer_fragment_container, new AddBeerFragment());
                    break;
            }
            ft.commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_beer, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this, "Back wer pressed", Toast.LENGTH_SHORT).show();
        Intent goHome = new Intent(this, HomeActivity.class);
        startActivity(goHome);
    }
}
