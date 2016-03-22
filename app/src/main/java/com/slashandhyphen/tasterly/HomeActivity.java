package com.slashandhyphen.tasterly;

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

    String TAG = "++Home Activity++";
    private SharedPreferences mPreferences;
    Button mAddBeerButton;
    Button mViewBeerButton;
    BeerDB dbTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

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
        if (!mPreferences.contains("AuthToken")) {
            Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.change_add_beer_fragment:
                Toast.makeText(getApplicationContext(),
                        "Clicked the change add beer option", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
