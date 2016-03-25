package com.slashandhyphen.tasterly;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Database.BeerDB;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    static String TAG = "++Home Activity++";
    private static SharedPreferences preferences;
    Button mAddBeerButton;
    Button mViewBeerButton;
    BeerDB dbTest;
    static SharedPreferences.Editor prefsEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        preferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);
        prefsEditor = preferences.edit();
        // Add default fragment for adding beers
        prefsEditor.putInt("AddBeerFragment", R.id.fragment_add_beer_alpha);
        prefsEditor.apply();


        mAddBeerButton = (Button) this.findViewById(R.id.addBeerButton);
        mViewBeerButton = (Button) this.findViewById(R.id.viewBeerButton);

        mAddBeerButton.setOnClickListener(this);
        mViewBeerButton.setOnClickListener(this);

        dbTest = new BeerDB(this);
    }

    @Override
    public void onClick(View v) {

        if (v == mAddBeerButton) {
            Toast.makeText(this, "Cannot add Beers", Toast.LENGTH_SHORT)
                    .show();

            Intent intent = new Intent(getApplicationContext(), AddBeerActivity.class);
            startActivity(intent);
            finish();
        }
        if (v == mViewBeerButton) {
            Toast.makeText(getApplicationContext(),
                    "Cannot view Beers", Toast.LENGTH_SHORT).show();
            String dbjunk = dbTest.expose();
            Toast.makeText(this, dbjunk, Toast.LENGTH_SHORT).show();
            Log.d(TAG, "Accessed the db.");
            Log.d(TAG, "It has this: " + dbjunk);
            Intent intent = new Intent(getApplicationContext(), ViewBeersActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        // Check if user already authenticated
        if (!preferences.contains("AuthToken")) {
            Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    // TODO Should probably be added to its own file for use in different activities
    public static class ChangeAddBeerViewDialog extends DialogFragment {
        final CharSequence[] items = {" alpha "," Geomancy mk. 1 "};

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Select The Difficulty Level");
            builder.setItems(items, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                    switch (item) {
                        case 0:
                            prefsEditor.putInt("AddBeerFragment", R.id.fragment_add_beer_alpha);
                            break;
                        case 1:
                            prefsEditor.putInt("AddBeerFragment", R.id.fragment_add_beer);
                            break;
                        default:
                            Toast.makeText(getActivity(),
                                    "Not sure how you got here...Check the " +
                                            "ChangeAddVeerViewDialog code",
                                    Toast.LENGTH_SHORT).show();
                            break;
                    }
                    prefsEditor.apply();
                    dismiss();
                }
            });
            return builder.create();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.change_add_beer_fragment:
                ChangeAddBeerViewDialog changeBeerDialog = new ChangeAddBeerViewDialog();
                FragmentManager fm = this.getFragmentManager();
                changeBeerDialog.show(fm, "MY FRACKING FRAGMENT!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
