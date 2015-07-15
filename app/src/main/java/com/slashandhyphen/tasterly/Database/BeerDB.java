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
 */
public class BeerDB extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Beer.db";
    private static final String TAG = "BeerDB";

    // Beer Data Table
    private static final String TABLE_BEER = "Beers";
    private static final String BEER_ID = "_id";
    public static final String BEER_NAME = "beer_name";

    // Flavor Data Table
    private static final String TABLE_FLAVOR = "Flavors";
    private static final String FLAVOR_ID = "_id";
    private static final String FLAVOR_NAME = "flavor_name";
    private static final String FLAVOR_VALUE = "flavor_value";

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

    public BeerDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d(TAG, "++BEER CONSTRUCTOR++");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "++ON CREATE++");

        // Beer Data Table
        db.execSQL(CREATE_BEER_TABLE);

        // Flavor Table
        db.execSQL(CREATE_FLAVOR_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void add(Beer mBeer) {
        Log.d(TAG, "++ADD BEER++");
        Log.d(TAG, "What you're trying to add is " + mBeer.getName());

        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        HashMap<String, Integer> flavors = mBeer.getFlavors();

        // Beer Table Add
        cv.put(BEER_NAME, mBeer.getName());
        db.insert(TABLE_BEER, null, cv);

        cv.clear();
        for(Map.Entry<String, Integer> flavor : flavors.entrySet()) {
            cv.put(BEER_NAME, mBeer.getName());
            cv.put(FLAVOR_NAME, flavor.getKey());
            cv.put(FLAVOR_VALUE, flavor.getValue());
            db.insert(TABLE_FLAVOR, null, cv);

            cv.clear();
        }
    }

    public Cursor getAllBeers() {
        Cursor curse;
        SQLiteDatabase db = getReadableDatabase();

        curse = db.query(TABLE_BEER, new String[] {BEER_ID, BEER_NAME}, null, null, null, null, null);
        if(curse != null) {
            curse.moveToFirst();
        }
        return curse;
    }

    public String expose() {
        Log.d(TAG, "++EXPOSE++");

        SQLiteDatabase db = getReadableDatabase();
        Cursor bCur = db.rawQuery("select * from " + TABLE_BEER, null);
        Cursor fCur;
        String data = "";

        Log.d(TAG, "Beer cursor has this many entries: " + bCur.getCount());


            while(bCur.moveToNext()) {
                data += (bCur.getString(bCur.getColumnIndex(BEER_NAME))) + "\n";

                fCur = db.rawQuery("select * from " + TABLE_FLAVOR
                        + " where " + BEER_NAME + "=\'" + data + "\';", null);

                while (fCur.moveToNext()) {
                    data += (fCur.getString((fCur.getColumnIndex(FLAVOR_NAME)))
                            + " " + fCur.getString(fCur.getColumnIndex(FLAVOR_VALUE))) + "\n";
                }
                fCur.close();
            }
        bCur.close();
        return data;
    }
}

