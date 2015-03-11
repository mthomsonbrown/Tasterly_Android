package com.slashandhyphen.tasterly;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends ActionBarActivity implements View.OnClickListener {

    private SharedPreferences mPreferences;
    Button mAddBeerButton;
    Button mViewBeerButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mPreferences = getSharedPreferences("CurrentUser", MODE_PRIVATE);

        mAddBeerButton = (Button) this.findViewById(R.id.addBeerButton);
        mViewBeerButton = (Button) this.findViewById(R.id.viewBeerButton);

        mAddBeerButton.setOnClickListener(this);
        mViewBeerButton.setOnClickListener(this);
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
        }
    }

    // Authentication asserted before this activity can be viewed
    @Override
    public void onResume() {
        super.onResume();

        if (mPreferences.contains("AuthToken")) {

        } else {
            Intent intent = new Intent(HomeActivity.this, AuthenticationActivity.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

}
