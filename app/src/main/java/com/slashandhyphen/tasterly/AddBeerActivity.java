package com.slashandhyphen.tasterly;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class AddBeerActivity extends Activity {
    String TAG = "++AddBeerActivity++";
    FragmentManager fm = getFragmentManager();
    Fragment fragment = fm.findFragmentById(R.id.add_beer_fragment_container);
    private SharedPreferences preferences;
    int defaultAddBeerFragment = R.id.fragment_add_beer_alpha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toast.makeText(this, "IN ON CREATE", Toast.LENGTH_SHORT).show();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beer);

        preferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        int addBeerFragmentId = preferences.getInt("AddBeerFragment", -1);
        if (addBeerFragmentId == -1) {
            addBeerFragmentId = defaultAddBeerFragment;
        }

        if (getFragmentManager().getBackStackEntryCount() == 0 && fragment == null) {
            FragmentTransaction ft = fm.beginTransaction();
            switch (addBeerFragmentId) {
                case R.id.fragment_add_beer:
                    Log.d(TAG, "Trying to add normal fragment");
                    ft.add(R.id.add_beer_fragment_container, new AddBeerFragment());
                    break;
                case R.id.fragment_add_beer_alpha:
                    Log.d(TAG, "Trying to add alpha fragment");
                    ft.add(R.id.add_beer_fragment_container, new AddBeerAlphaFragment());
                    break;
                default:
                    Log.d(TAG, "Trying to add default fragment in default");
                    ft.add(R.id.add_beer_fragment_container, new AddBeerAlphaFragment());
                    break;
            }
            ft.addToBackStack("First Fragment");
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
        // TODO This works okay, but feels a little inelegant.  The backstack bit I guess is fine,
        // but I shouldn't need to know what activity to navigate to if the backstack is empty.
        if(getFragmentManager().getBackStackEntryCount() == 1) {
            Intent goHome = new Intent(this, HomeActivity.class);
            startActivity(goHome);
            finish();
        }
        else {
            getFragmentManager().popBackStack();
        }
    }
}
