package com.slashandhyphen.tasterly;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.slashandhyphen.tasterly.Database.BeerDB;
import com.slashandhyphen.tasterly.Models.Beer;
import com.slashandhyphen.tasterly.Models.BeerTest;
import com.slashandhyphen.tasterly.Models.SessionResponse;

import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class ViewBeersActivity extends ActionBarActivity implements View.OnClickListener {
    String TAG = "ViewBeersActivity";
    BeerDB beerDB;
    SimpleCursorAdapter curseAdapter;
    Button uploadButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_beers);

        beerDB = new BeerDB(this);
        displayListView();

        uploadButton = (Button) findViewById(R.id.upload);
        uploadButton.setOnClickListener(this);
    }

    /**
     * I have no idea what this does, but it does it poorly.  I'm calling a DB field in order to
     * generate a cursor to data, rather than having a function inside the DB class that uses
     * the BEER_NAME to generate a cursor
     */
    private void displayListView() {
        String[] columns = new String[] {
                // TODO this is bad
                BeerDB.getBeerName()
        };

        int[] views = new int[] {
            R.id.text_beer_name
        };

        Cursor curse = beerDB.getAllBeers();

        curseAdapter = new SimpleCursorAdapter(
                this, R.layout.list_item_beer,
                curse,
                columns,
                views,
                0
        );

        ListView listView = (ListView) findViewById(R.id.beer_list);

        listView.setAdapter(curseAdapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_view_beers, menu);
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

    /**
     * Right now, this is just uploading a test beer object.
     * @param v The button I clicked
     */
    @Override
    public void onClick(View v) {
        Toast.makeText(this, "Hit the button", Toast.LENGTH_SHORT).show();

        Beer beer = beerDB.getBeer(0);         // Hardcoded sanity check
        BeerTest beerTest = new BeerTest();    // Hardercoded sanity check


        RestAdapter.Builder builder = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.railsEndpoint))
                .setRequestInterceptor(new RequestInterceptor() {
                    @Override
                    public void intercept(RequestInterceptor.RequestFacade request) {
                        String token = getSharedPreferences("CurrentUser", MODE_PRIVATE)
                                .getString("AuthToken", null);

                        request.addHeader("Authorization", "Token token=" + token);
                    }
                });

        RestAdapter restAdapter = builder.build();

        AuthenticationService service =
                restAdapter.create(AuthenticationService.class);

        service.addBeer(beerTest, new Callback<SessionResponse>() {
            @Override
            public void success(SessionResponse result, Response response){
                Toast.makeText(getApplicationContext(),
                        "Looks like you did it, buddy!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError retrofitError) {
                Toast.makeText(getApplicationContext(),
                        "Probably a 401...", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
