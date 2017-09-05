package com.example.anamariapaula.myastroweather;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.xml.datatype.Duration;

public class FavouritesLocationsActivity extends AppCompatActivity implements OnItemSelectedListener {

    Spinner spinner_favourites_locations;
    Spinner spinner_founded_locations;

    EditText editText_find_location;

    Button button_find_location;
    Button button_add_to_favourites;
    Button button_remove_from_favourites;

    ArrayList<Location> favouritesLocations;
    ArrayList<Location> foundedLocations = new ArrayList<>();
    ArrayList<String> foundedLocationsStrings = new ArrayList<>();

    ArrayAdapter<String> dataAdapterFoundedLocations;

    DBHandler db;

    GettingLocationsFromYahoo gettingLocationsFromYahoo = new GettingLocationsFromYahoo();

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

        spinner_founded_locations.setOnItemSelectedListener(this);
        dataAdapterFoundedLocations = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, foundedLocationsStrings);

        // Drop down layout style - list view with radio button
        dataAdapterFoundedLocations.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_founded_locations.setAdapter(dataAdapterFoundedLocations);

        //TODO sorawdzenie połączenia z internetem, zablokować przyciski w razie potrzeby
    }

    void setSpinner_favourites_locations() {
        //TODO dokończyć
    }

    void setSpinner_founded_locations(ArrayList<Location> locations) {
        //TODO dokończyć
    }

    public void onButtonClick_find_location(View v) {

        dataAdapterFoundedLocations.clear();
        String text = editText_find_location.getEditableText().toString();
        try {
            GettingLocationsFromYahoo task = new GettingLocationsFromYahoo();
            foundedLocations = task.doInBackground(text);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (foundedLocations.size() == 0) {
            Toast.makeText(this.getBaseContext(), "Wrong data entered", Toast.LENGTH_SHORT);
        } else {
            foundedLocationsStrings = locationListToString();
            dataAdapterFoundedLocations.addAll(foundedLocationsStrings);
        }
//        spinner_founded_locations.setAdapter(dataAdapterFoundedLocations);
    }

    private ArrayList<String> locationListToString() {
        ArrayList<String> out = new ArrayList<>();
        for (Location l : foundedLocations) {
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
        //TODO dokończyć
    }

    public void onButtonClick_remove_from_favourites(View v) {
        //TODO dokończyć
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
