package com.example.anamariapaula.myastroweather;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

public class FavouritesLocationsActivity extends AppCompatActivity implements OnItemSelectedListener {

    private static final String PREFS_NAME = "ShPe";
    Spinner spinner_favourites_locations;
    Spinner spinner_founded_locations;

    EditText editText_find_location;

    Button button_find_location;
    Button button_add_to_favourites;
    Button button_remove_from_favourites;

    ArrayList<Location> favouritesLocations;
    ArrayList<Location> foundedLocations = new ArrayList<>();
    ArrayList<String> favouritesLocationsStrings = new ArrayList<>();
    ArrayList<String> foundedLocationsStrings = new ArrayList<>();

    ArrayAdapter<String> dataAdapterFoundedLocations;
    ArrayAdapter<String> dataAdapterFavouritesLocations;

    DBHandler db;

    GettingLocationsFromYahoo gettingLocationsFromYahoo = new GettingLocationsFromYahoo();

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_locations);
    }

    @Override
    protected void onResume() {
        super.onResume();

        editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        db = new DBHandler(this);

        favouritesLocations = (ArrayList<Location>) db.getAllLocations();
        favouritesLocationsStrings = locationListToString(favouritesLocations);

        spinner_favourites_locations = (Spinner) findViewById(R.id.spinner_favourites_locations);
        spinner_founded_locations = (Spinner) findViewById(R.id.spinner_founded_locations);
        editText_find_location = (EditText) findViewById(R.id.editText_find_location);
        button_find_location = (Button) findViewById(R.id.button_find_location);
        button_add_to_favourites = (Button) findViewById(R.id.button_add_to_favourites);
        button_remove_from_favourites = (Button) findViewById(R.id.button_remove_from_favourites);

        spinner_favourites_locations.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (favouritesLocations.size() > 0) {
                    editor.putInt("woeid", favouritesLocations.get(spinner_favourites_locations.getSelectedItemPosition()).getWOEID());
                    editor.apply();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
        spinner_founded_locations.setOnItemSelectedListener(this);
//        spinner_favourites_locations.setOnItemSelectedListener(this);
        dataAdapterFoundedLocations = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foundedLocationsStrings);
        dataAdapterFavouritesLocations= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, favouritesLocationsStrings);
        // Drop down layout style - list view with radio button
        dataAdapterFoundedLocations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dataAdapterFavouritesLocations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_founded_locations.setAdapter(dataAdapterFoundedLocations);
        spinner_favourites_locations.setAdapter(dataAdapterFavouritesLocations);

        if (Utils.isOnline(this)) {
            button_find_location.setEnabled(true);
            button_add_to_favourites.setEnabled(true);
            spinner_founded_locations.setEnabled(true);
            editText_find_location.setEnabled(true);
        } else {
            button_find_location.setEnabled(false);
            button_add_to_favourites.setEnabled(false);
            spinner_founded_locations.setEnabled(false);
            editText_find_location.setEnabled(false);
        }
    }

    void setSpinner_favourites_locations() {
        //TODO dokończyć
    }

    void setSpinner_founded_locations(ArrayList<Location> locations) {
        //TODO dokończyć
    }

    public void onButtonClick_find_location(View v) {
//gj
        dataAdapterFoundedLocations.clear();
        String text = editText_find_location.getEditableText().toString();
        if (text != null && !text.equalsIgnoreCase("")) {
            try {
                GettingLocationsFromYahoo task = new GettingLocationsFromYahoo();
                foundedLocations = task.doInBackground(text);
            } catch (Exception e) {
                Toast.makeText(this.getBaseContext(), "Wrong data entered", Toast.LENGTH_SHORT).show();
            }

            if (foundedLocations.size() == 0) {
                Toast.makeText(this.getBaseContext(), "Wrong data entered", Toast.LENGTH_SHORT).show();
            } else {
                foundedLocationsStrings = locationListToString(foundedLocations);
                dataAdapterFoundedLocations.addAll(foundedLocationsStrings);
            }
        }
//        spinner_founded_locations.setAdapter(dataAdapterFoundedLocations);
    }

    private ArrayList<String> locationListToString(ArrayList<Location> locationsList) {
        ArrayList<String> out = new ArrayList<>();
        for (Location l : locationsList) {
            StringBuilder sb = new StringBuilder();
            if (l.getLocality2() != null) {
                sb.append(l.getLocality2() + ", ");
            }
            if (l.getLocality1() != null) {
                sb.append(l.getLocality1() + ", ");
            }
            if (l.getAdmin3() != null) {
                sb.append(l.getAdmin3() + ", ");
            }
            if (l.getAdmin2() != null) {
                sb.append(l.getAdmin2() + ", ");
            }
            if (l.getAdmin1() != null) {
                sb.append(l.getCountry());
            }
            out.add(sb.toString());
        }
        return out;
    }

    public void onButtonClick_add_to_favourites(View v) {
        if (foundedLocations.size() > 0) {
            Location location = foundedLocations.get(spinner_founded_locations.getSelectedItemPosition());
            if (favouritesLocations!=null && favouritesLocations.size()>0) {
                for (Location l : favouritesLocations) {
                    if (l.getWOEID() == location.getWOEID()) {
                        return;
                    }
                }
            }
            db.addLocation(location);
            favouritesLocations = (ArrayList<Location>) db.getAllLocations();
            favouritesLocationsStrings = locationListToString(favouritesLocations);
            dataAdapterFavouritesLocations.clear();
            dataAdapterFavouritesLocations.addAll(favouritesLocationsStrings);
        }
    }

    public void onButtonClick_remove_from_favourites(View v) {
        if (favouritesLocations.size() > 0) {
            Location location = favouritesLocations.get(spinner_favourites_locations.getSelectedItemPosition());
           // db.deleteLocation(location);

            db.deleteLocation(location);

            favouritesLocations = (ArrayList<Location>) db.getAllLocations();
            favouritesLocationsStrings = locationListToString(favouritesLocations);
            if (favouritesLocationsStrings.size() == 0) {
                db.resetTable();
                dataAdapterFavouritesLocations.clear();
            } else {
                dataAdapterFavouritesLocations.clear();
                dataAdapterFavouritesLocations.addAll(favouritesLocationsStrings);
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Log.e("position", Integer.toString(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
