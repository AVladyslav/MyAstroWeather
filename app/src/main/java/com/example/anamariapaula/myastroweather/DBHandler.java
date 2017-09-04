package com.example.anamariapaula.myastroweather;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Visual on 04.09.2017.
 */

public class DBHandler extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "locationsInfo";
    // Contacts table name
    private static final String TABLE_LOCATIONS = "locations";
    // Locations Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_COUNTRY = "country";
    private static final String KEY_ADMIN1 = "admin1";
    private static final String KEY_ADMIN2 = "admin2";
    private static final String KEY_ADMIN3 = "admin3";
    private static final String KEY_LOCALITY1 = "locality1";
    private static final String KEY_LOCALITY2 = "locality2";
    private static final String KEY_WOEID = "woeid";

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_LOCATIONS_TABLE = "CREATE TABLE " + TABLE_LOCATIONS + "("
        + KEY_ID + " INTEGER PRIMARY KEY," + KEY_COUNTRY + " TEXT," + KEY_ADMIN1 + " TEXT," + KEY_ADMIN2 + " TEXT,"
        + KEY_ADMIN3 + " TEXT," + KEY_LOCALITY1 + " TEXT," + KEY_LOCALITY2 + " TEXT," + KEY_WOEID + " TEXT" + ")";
        db.execSQL(CREATE_LOCATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        // Creating tables again
        onCreate(db);
    }

    public void addLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COUNTRY, location.getCountry()); // Country
        values.put(KEY_ADMIN1, location.getAdmin1()); // admin1
        values.put(KEY_ADMIN2, location.getAdmin2()); // admin2
        values.put(KEY_ADMIN3, location.getAdmin3()); // admin3
        values.put(KEY_LOCALITY1, location.getLocality1()); // locality1
        values.put(KEY_LOCALITY2, location.getLocality2()); // locality2
        values.put(KEY_WOEID, location.getWOEID()); // WHOEID
        // Inserting Row
        db.insert(TABLE_LOCATIONS, null, values);
        db.close(); // Closing database connection
    }

    public Location getLocation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] COLUMNS = new String[] { KEY_ID, KEY_COUNTRY, KEY_ADMIN1, KEY_ADMIN2, KEY_ADMIN3, KEY_LOCALITY1, KEY_LOCALITY2, KEY_WOEID};
        String[] SELECTION_ARGS = new String[] { String.valueOf(id)};
        Cursor cursor = db.query(TABLE_LOCATIONS, COLUMNS, KEY_ID + "=?", SELECTION_ARGS, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }

        Location location = new Location(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5), cursor.getString(6), Integer.parseInt(cursor.getString(7)));
        // return location
        return location;
    }

    public List<Location> getAllLocations() {
        List<Location> locationList = new ArrayList<Location>();
        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_LOCATIONS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Location location = new Location();
                location.setId(Integer.parseInt(cursor.getString(0)));
                location.setCountry(cursor.getString(1));
                location.setAdmin1(cursor.getString(2));
                location.setAdmin2(cursor.getString(3));
                location.setAdmin3(cursor.getString(4));
                location.setLocality1(cursor.getString(5));
                location.setLocality2(cursor.getString(6));
                location.setId(Integer.parseInt(cursor.getString(7)));
                // Adding contact to list
                locationList.add(location);
            } while (cursor.moveToNext());
        }
        // return contact list
        return locationList;
    }

    public int getLocationsCount() {
        String countQuery = "SELECT * FROM " + TABLE_LOCATIONS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();
        // return count
        return cursor.getCount();
    }

    public int updateLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_COUNTRY, location.getCountry());
        values.put(KEY_ADMIN1, location.getAdmin1());
        values.put(KEY_ADMIN2, location.getAdmin2());
        values.put(KEY_ADMIN3, location.getAdmin3());
        values.put(KEY_LOCALITY1, location.getLocality1());
        values.put(KEY_LOCALITY2, location.getLocality2());
        values.put(KEY_WOEID, location.getWOEID());
        // updating row
        return db.update(TABLE_LOCATIONS, values, KEY_ID + " = ?",
                new String[]{String.valueOf(location.getId())});
    }

    public void deleteLocation(Location location) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_LOCATIONS, KEY_ID + " = ?",
                new String[] { String.valueOf(location.getId()) });
        db.close();
    }
}
