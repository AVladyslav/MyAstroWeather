package com.example.anamariapaula.myastroweather;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Ana Maria Paula on 09.06.2017.
 */

public class Settings extends AppCompatActivity {

    Bundle bundle;
    String longitude;
    String latitude;
    int minutesFrequency;
    int hoursFrequency;
    EditText hours;
    EditText minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getIntent().getExtras();
        setContentView(R.layout.settings);
    }

    @Override
    protected void onResume() {
        super.onResume();

        longitude = bundle.getString("longitude");
        latitude = bundle.getString("latitude");
        hoursFrequency = Integer.parseInt(bundle.getString("updateTimeHours"));
        minutesFrequency = Integer.parseInt(bundle.getString("updateTimeMinutes"));

        hours = (EditText) findViewById(R.id.hours);
        hours.setText(Integer.toString(hoursFrequency ));
        minutes = (EditText) findViewById(R.id.minutes);
        minutes.setText(Integer.toString(minutesFrequency));
    }

    public void onButtonClick_OK(View v) {
        if (hours.getText().toString().equalsIgnoreCase("") || minutes.getText().toString().equalsIgnoreCase("")) {
            Toast.makeText(getBaseContext(), "Enter values" , Toast.LENGTH_SHORT ).show();
        } else {
            hoursFrequency = Integer.parseInt(hours.getText().toString());
            minutesFrequency = Integer.parseInt(minutes.getText().toString());
            if (minutesFrequency < 59) {
                if (hoursFrequency == 0 && minutesFrequency == 0) {
                    Toast.makeText(getBaseContext(), "Enter correct values" , Toast.LENGTH_SHORT ).show();
                } else {
                    Intent intent = new Intent(Settings.this, AstroWeather.class);
                    intent.putExtra("longitude", longitude);
                    intent.putExtra("latitude", latitude);
                    intent.putExtra("updateTimeHours", Integer.toString(hoursFrequency));

                    intent.putExtra("updateTimeMinutes", Integer.toString(minutesFrequency));
                    startActivity(intent);
                    Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
                }
            }
        }


        /*
        EditText longitude_text = (EditText) findViewById(R.id.editText_longitude);
        EditText latitude_text = (EditText) findViewById(R.id.editText_latitude);

        if(longitude_text.getText().toString().equals("") || latitude_text.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Enter values" , Toast.LENGTH_SHORT ).show();
        } else {

            Intent intent = new Intent(MainActivity.this, AstroWeather.class);
            intent.putExtra("longitude", longitude_text.getText().toString());
            intent.putExtra("latitude", latitude_text.getText().toString());
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

        */
    }
}