package com.slashandhyphen.tasterly;

import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import com.slashandhyphen.tasterly.Database.BeerDB;
import com.slashandhyphen.tasterly.Models.Beer;


public class ViewBeersActivity extends ActionBarActivity {
    String TAG = "ViewBeersActivity";
    BeerDB beerDB;
    SimpleCursorAdapter curseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_beers);

        beerDB = new BeerDB(this);
        Log.d(TAG, "Did it fuck up there?");
        displayListView();
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
}
