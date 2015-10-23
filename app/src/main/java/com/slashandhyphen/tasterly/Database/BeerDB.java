package com.slashandhyphen.tasterly.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.slashandhyphen.tasterly.Models.Beer;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ookamijin on 4/6/2015.
 *
 * <P>
 * This class creates a database holding a list of beers along with their objective
 * characteristics i.e. ABV, brewery, etc.  It also holds subjective information the user records
 * about their tasting experience.
 *
 * This class also contains the API for other classes to interact with the
 * database by inserting new beers, and adding subjective user input on flavor, pictures, etc.
 * </P>
 */
public class BeerDB extends SQLiteOpenHelper {
    public static final String TAG = "BeerDB";
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Beer.db";

    // Beer Data Table
    private static final String TABLE_BEER = "Beers";
    private static final String BEER_ID = "_id";
    private static final String BEER_NAME = "beer_name";

    // Flavor Data Table
    private static final String TABLE_FLAVOR = "Flavors";
    private static final String FLAVOR_ID = "_id";
    private static final String FLAVOR_NAME = "flavor_name";
    private static final String FLAVOR_VALUE = "flavor_value";

    /*
     * These are setting up relationships between the different tables, as well as describing the
     * contents of each table.
     */
    private static final String CREATE_BEER_TABLE = "create table "
            + TABLE_BEER + "("
            + BEER_ID + " integer primary key autoincrement, "
            + BEER_NAME + " text not null);";

    private static final String CREATE_FLAVOR_TABLE = "create table "
            + TABLE_FLAVOR + "("
            + FLAVOR_ID + " integer primary key autoincrement, "
            + BEER_NAME + " text not null, "
            + FLAVOR_NAME + " text not null, "
            + FLAVOR_VALUE + " int);";

    /**
     * Constructor for the BeerDB
     *
     * @param context handle to calling application's (activity's) resources
     */
    public BeerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "++BEER CONSTRUCTOR++");
    }

    public static String getBeerName() {
        return BEER_NAME;
    }

    public static String getFlavorName() {
        return FLAVOR_NAME;
    }

    /**
     * This creates a new database and the tables described in this class' TABLE strings
     *
     * @param db Database to create tables in
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "++ON CREATE++");

        // Beer Data Table
        db.execSQL(CREATE_BEER_TABLE);

        // Flavor Table
        db.execSQL(CREATE_FLAVOR_TABLE);
    }

    /**
     * This class won't be implemented unless absolutely necessary.
     *
     * It creates a merge file for different schemas of this database, but hopefully because
     * I'm Batman, I'll never have to refactor the live database.
     *
     * @param db Database to merge
     * @param oldVersion version number to upgrade from
     * @param newVersion version number to upgrade to
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    /**
     * This inserts a new beer into the beer table and fills out a flavor table entry associated
     * with that beer in the flavor table.  It looks like a massive source of bugs, and should
     * probably contain a lot of exception handling.
     *
     * @param mBeer A storage class for aspects of the beer to be pushed to the database
     */
    public void add(Beer mBeer) {
        Log.d(TAG, "++ADD BEER++");
        Log.d(TAG, "What you're trying to add is " + mBeer.getName());

        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();

        // This adds a new row to the beer table and assigns the name of the beer to it
        cv.put(BEER_NAME, mBeer.getName());
        db.insert(TABLE_BEER, null, cv);

        // Clear CV in order to reuse for flavor table entry
        cv.clear();

        /*
         * This iterates over a map of flavors and inserts each flavor as a new entry to
         * the flavor table associated with a beer
         */
        for(Map.Entry<String, Integer> flavor : mBeer.getFlavors().entrySet()) {
            cv.put(BEER_NAME, mBeer.getName());
            cv.put(FLAVOR_NAME, flavor.getKey());
            cv.put(FLAVOR_VALUE, flavor.getValue());
            db.insert(TABLE_FLAVOR, null, cv);

            cv.clear();
        }
    }

    /**
     * Returns a cursor to a list of all beer ids and names in the database.
     * TODO rename or add all available fields to the returned cursor
     *
     * @return Cursor A list of all beer names and ids in the DB
     */
    public Cursor getAllBeers() {
        Cursor curse;
        SQLiteDatabase db = getReadableDatabase();

        // Build a cursor containing a list of all beer names and beer ids in the beer table
        curse = db.query(TABLE_BEER, new String[] {BEER_ID, BEER_NAME},
                null, null, null, null, null);
        if(curse != null) {
            curse.moveToFirst();
        }
        return curse;
    }


    /**
     * Returns some garbled string of beer names and flavors in the form of a string.
     * Extravagant sanity check I believe.
     *
     * TODO rename because expose is very vague and this doesn't do what that word suggests it does
     * TODO after that, also delete this method because it's not useful really, save for debugging
     *
     * @return String A list of all the names and flavors of beers in the DB
     */
    public String expose() {
        Log.d(TAG, "++EXPOSE++");

        // Select every entry in TABLE_BEER
        SQLiteDatabase db = getReadableDatabase();
        Cursor bCur = db.rawQuery("select * from " + TABLE_BEER, null);
        Cursor fCur;
        String data = "";

        Log.d(TAG, "Beer cursor has this many entries: " + bCur.getCount());


            while(bCur.moveToNext()) {
                Log.d(TAG, "In while loop");
                data += (bCur.getString(bCur.getColumnIndex(BEER_NAME))) + "\n";

                fCur = db.rawQuery("select * from " + TABLE_FLAVOR +
                        " where " + BEER_NAME + "=\'" +
                        bCur.getString(bCur.getColumnIndex(BEER_NAME)) + "\';", null);

                while (fCur.moveToNext()) {
                    data += (fCur.getString((fCur.getColumnIndex(FLAVOR_NAME)))
                            + " " + fCur.getString(fCur.getColumnIndex(FLAVOR_VALUE))) + "\n";
                }
                fCur.close();
            }
        bCur.close();
        Log.d(TAG, "About to return from exposing myself");
        return data;
    }
}

