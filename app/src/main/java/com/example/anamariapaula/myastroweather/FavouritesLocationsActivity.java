package com.example.anamariapaula.myastroweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class FavouritesLocationsActivity extends AppCompatActivity {

    Spinner spinner_favourites_locations;
    Spinner spinner_founded_locations;

    EditText editText_find_location;

    Button button_find_location;
    Button button_add_to_favourites;
    Button button_remove_from_favourites;

    ArrayList<Location> favouritesLocations;

    DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites_locations);
    }

    @Override
    protected void onResume() {
        super.onResume();

        db = new DBHandler(this);

        favouritesLocations = (ArrayList<Location>) db.getAllLocations();
        spinner_favourites_locations = (Spinner) findViewById(R.id.spinner_favourites_locations);
        spinner_founded_locations = (Spinner) findViewById(R.id.spinner_founded_locations);
        editText_find_location = (EditText) findViewById(R.id.editText_find_location);
        button_find_location = (Button) findViewById(R.id.button_find_location);
        button_add_to_favourites = (Button) findViewById(R.id.button_add_to_favourites);
        button_remove_from_favourites = (Button) findViewById(R.id.button_remove_from_favourites);
    }
}
