package com.example.anamariapaula.myastroweather;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String PREFS_NAME = "mySharedPreferences";
    Bundle bundle;
    String longitude;
    String latitude;
    String updateTimeMinutes;
    String updateTimeHours;

    Integer minutesFrequency = 15;  //Defaultowy czas odswiezania (minuty)
    Integer hoursFrequency = 0;     //Defaultowy czas odswiezania (godziny)

    EditText longitude_text;
    EditText latitude_text;

    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        bundle = getIntent().getExtras();



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        internetConnectionInfo();
    }

    @Override
    protected void onResume() {
        super.onResume();
        longitude_text = (EditText) findViewById(R.id.editText_longitude);
        latitude_text = (EditText) findViewById(R.id.editText_latitude);

        if (bundle != null) {
            longitude = bundle.getString("longitude");
            latitude = bundle.getString("latitude");
            updateTimeMinutes = bundle.getString("updateTimeMinutes");
            updateTimeHours = bundle.getString("updateTimeHours");
            longitude_text.setText(longitude);
            latitude_text.setText(latitude);
        } else {
            updateTimeMinutes = minutesFrequency.toString();
            updateTimeHours = hoursFrequency.toString();
        }
    }

    public void onButtonClick_C(View v) {



        if(longitude_text.getText().toString().equals("") || latitude_text.getText().toString().equals("")) {
            Toast.makeText(getBaseContext(), "Enter values" , Toast.LENGTH_SHORT ).show();
        } else {

            editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();

            if (Utils.isOnline(this)) {
                LocationGetter task = new LocationGetter();
                com.example.anamariapaula.myastroweather.Location location = task.doInBackground(latitude_text.getText().toString(), longitude_text.getText().toString());
                editor.putInt("woeid", location.getWOEID());
                editor.apply();
            }

            Intent intent = new Intent(MainActivity.this, AstroWeather.class);
            intent.putExtra("longitude", longitude_text.getText().toString());
            intent.putExtra("latitude", latitude_text.getText().toString());
            intent.putExtra("updateTimeMinutes", updateTimeMinutes);
            intent.putExtra("updateTimeHours", updateTimeHours);
            startActivity(intent);
            Toast.makeText(getBaseContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

    private void internetConnectionInfo() {
        if (!Utils.isOnline(this)) {
            Utils.noInternetConnectionInfo(this);
        }
    }
}
