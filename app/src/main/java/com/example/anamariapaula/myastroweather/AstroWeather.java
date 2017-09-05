package com.example.anamariapaula.myastroweather;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.astrocalculator.AstroCalculator;
import com.astrocalculator.AstroDateTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;

import data.FetchWeatherData;
import model.KnownLocations;
import model.Location;
import model.Weather;
import utility.DefaultValue;

//AppCompatActivity
public class AstroWeather extends AppCompatActivity  implements ViewPager.OnPageChangeListener{

    private int minutesFrequency;
    private int hoursFrequency;
    private int lastRefreshedMinutes = 0;
    private int lastRefreshedHours = 0;

    Timer rTimer;
    Timer cTimer;

    double longitude = 0;
    double latitude = 0;
    ViewPager pager;
    AstroDateTime current_time = new AstroDateTime();
    AstroCalculator calculator;
    Calendar calendar;
    Calendar newRefresh;
    Bundle bundle;
    MyAdapter adapter;

    FragmentSun firstFragment;
    FragmentMoon secondFragment;
    Basic_information thirdFragment;
    Additional_information fourthFragment;
    Weather_forecast fifthFragment;

    long currTime = System.currentTimeMillis();
    long updateTimeFromFile = 0;
    float refresh = 15f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_astro_weather);

        try {
            File file = getBaseContext().getFileStreamPath("updateTime");
            if(!file.exists()){//jeśli nie ma, to stworz
                Log.d("XXXXXXXXXXXXXX", "NIE Znalazl pliku");
                FileOutputStream outputStream;
                outputStream = this.openFileOutput("updateTime", Context.MODE_PRIVATE);
                outputStream.write(String.valueOf(currTime).getBytes());

                outputStream.close();
            }
            Log.d("XXXXXXXXXXXXXX", "Znalazl plik");
            FileInputStream fileInputStream = this.openFileInput("updateTime");
            StringBuilder stringBuilder = new StringBuilder();
            int ch;
            while ((ch = fileInputStream.read()) != -1) {
                stringBuilder.append((char) ch);
            }
            updateTimeFromFile = Long.parseLong(stringBuilder.toString());

        }
        catch (Exception e){
            Log.d("XXXXXXXXXXXXXX", "Jestesmy w catchu");
        }
        long time = (currTime - updateTimeFromFile)/60000;
        Log.d("XXXXXXXXXXXXXX", "TIME:" + String.valueOf(time));
        //sprawdzenie dostępu do internetu

        if(isConnected() && (time > 15 || time == 0)) {     //źle sie dzieje
            //internetConnection = true;
            Toast toast = Toast.makeText(getApplicationContext(),"Aktualizowanie danych", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
            toast.show();

            new SaveDataAsyncTask(this).execute();
        }
        else if(isConnected()){
            Toast toast = Toast.makeText(getApplicationContext(),"Dane aktualne", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
            toast.show();
        }
        else {
            //internetConnection = false;
            Toast toast = Toast.makeText(getApplicationContext(),"Brak połączenia z internetem. Dane mogą być nieaktualne.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
            toast.show();
        }

        bundle = getIntent().getExtras();
        longitude = Double.parseDouble(bundle.getString("longitude"));
        latitude = Double.parseDouble(bundle.getString("latitude"));
        minutesFrequency = Integer.parseInt(bundle.getString("updateTimeMinutes"));
        hoursFrequency = Integer.parseInt(bundle.getString("updateTimeHours"));
        TextView text_long = (TextView) findViewById(R.id.longitude_view);
        TextView text_lat = (TextView) findViewById(R.id.latitude_view);

        text_long.setText(Double.toString(longitude));
        text_lat.setText(Double.toString(latitude));
        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
            pager = (ViewPager) findViewById(R.id.view_pager);
            adapter = new MyAdapter(getSupportFragmentManager());
            pager.addOnPageChangeListener(this);
            adapter.setIsNeedToUpdate(true);
        }
        else {
            if (findViewById(R.id.fragment_container_sun) != null) {

                firstFragment = new FragmentSun();

                firstFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_sun, firstFragment).commit();
            }

            if (findViewById(R.id.fragment_container_moon) != null) {

                secondFragment = new FragmentMoon();

                secondFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_moon, secondFragment).commit();
            }

            if (findViewById(R.id.fragment_container_basic) != null) {

                thirdFragment = new Basic_information();

                thirdFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_basic, thirdFragment).commit();
            }

            if (findViewById(R.id.fragment_container_additional) != null) {

                fourthFragment = new Additional_information();

                fourthFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_additional, fourthFragment).commit();
            }

            if (findViewById(R.id.fragment_container_forecast) != null) {

                fifthFragment = new Weather_forecast();

                fifthFragment.setArguments(getIntent().getExtras());

                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container_forecast, fifthFragment).commit();
            }
        }


        calendar = Calendar.getInstance();
        startClock();
        refreshTimer();
        current_time = getCurrentTime();

        getSunInfo();
        lastRefreshedMinutes = calendar.MINUTE;
        lastRefreshedHours = calendar.HOUR;
        newRefresh = Calendar.getInstance();
        newRefresh.add(Calendar.HOUR, hoursFrequency);
        newRefresh.add(Calendar.MINUTE, minutesFrequency);
    }

    @Override
    protected void onResume() {
        super.onResume();

        long time = (currTime - updateTimeFromFile)/60000;
        Log.d("XXXXXXXXXXXXXX", "TIME:" + String.valueOf(time));
        //sprawdzenie dostępu do internetu

        if(isConnected()) {
            new SaveDataAsyncTask(this).execute();
        }

        else {
            //internetConnection = false;
            Toast toast = Toast.makeText(getApplicationContext(),"Brak połączenia z internetem. Dane mogą być nieaktualne.", Toast.LENGTH_LONG);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER,0,0);
            toast.show();
        }

        SharedPreferences sharedpreferences = getSharedPreferences("ShPe", Context.MODE_PRIVATE);
        latitude = sharedpreferences.getFloat("szerokosc", 51.766024f);
        longitude = sharedpreferences.getFloat("dlugosc", 19.453807f);
        refresh = sharedpreferences.getFloat("czas", 15.0f);
        getCurrentTime();

    }

    public AstroDateTime getCurrentTime() {

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;   //index starts from 0
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        return new AstroDateTime(year, month, day, hour, minute, second, 0, false);
    }

    private void startClock() {

        cTimer = new Timer();
        cTimer.scheduleAtFixedRate(
                new java.util.TimerTask() {
                    @Override
                    public void run() {

//                        final String time = tempCalendar.getTime().getSeconds();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Calendar tempCalendar = Calendar.getInstance();
                                String time = String.format("%02d:%02d:%02d", tempCalendar.getTime().getHours(), tempCalendar.getTime().getMinutes(), tempCalendar.getTime().getSeconds());
                                TextView textViewTime = (TextView) findViewById(R.id.textClock);
                                textViewTime.setText(time);
                            }
                        });
                    }
                },0 , 1000  //1 sekunda = 1000ms
        );
    }

    private void refreshTimer() {
        rTimer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        calendar = Calendar.getInstance();
                        Toast.makeText(getBaseContext(), "Refreshed", Toast.LENGTH_SHORT).show();
                        if ((getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_NORMAL) {
                            pager.setAdapter(adapter);
                        } else {
                            firstFragment.newInstance("Fragment Sun", true);
                            refreshSunFragment();
                            refreshMoonFragment();
                        }
                    }
                });
            }
        };
        int milis = (hoursFrequency * 60 + minutesFrequency) * 60 * 1000;
        rTimer.scheduleAtFixedRate(task, 0, milis);
    }

    private void refreshSunFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container_sun, firstFragment.newInstance("", true));
        transaction.addToBackStack(null);

// Commit the transaction
        transaction.commit();
    }


    private void refreshMoonFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

// Replace whatever is in the fragment_container view with this fragment,
// and add the transaction to the back stack so the user can navigate back
        transaction.replace(R.id.fragment_container_moon, secondFragment.newInstance("Moon Fragment", true));

// Commit the transaction
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        super.onCreateOptionsMenu(menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        cTimer.cancel();
        rTimer.cancel();
        if(item.getItemId() == R.id.action_location) {
            Intent intent = new Intent(AstroWeather.this, MainActivity.class);
            intent.putExtra("longitude", Double.toString(longitude));
            intent.putExtra("latitude", Double.toString(latitude));
            intent.putExtra("updateTimeMinutes", Integer.toString(minutesFrequency));
            intent.putExtra("updateTimeHours", Integer.toString(hoursFrequency));
            startActivity(intent);
        } else if(item.getItemId() == R.id.action_frequency) {
            Intent intent = new Intent(AstroWeather.this, Settings.class);
            intent.putExtra("longitude", Double.toString(longitude));
            intent.putExtra("latitude", Double.toString(latitude));
            intent.putExtra("updateTimeMinutes", Integer.toString(minutesFrequency));
            intent.putExtra("updateTimeHours", Integer.toString(hoursFrequency));
            startActivity(intent);
        } else if(item.getItemId() == R.id.action_exit) {
            finishAffinity();
        } else if (item.getItemId() == R.id.action_locations) {
            Intent intent = new Intent(AstroWeather.this, FavouritesLocationsActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                pager.setCurrentItem(0);
                break;
            case 1:
                pager.setCurrentItem(1);
                break;
            case 2:
                pager.setCurrentItem(2);
                break;
            case 3:
                pager.setCurrentItem(3);
                break;
            case 4:
                pager.setCurrentItem(4);
                break;
            default:
                pager.setCurrentItem(0);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float v, int i) {

    }

    @Override
    public void onPageScrollStateChanged(int position) {

    }

    public void getSunInfo() {

        calculator = new AstroCalculator(current_time, new AstroCalculator.Location(latitude, longitude));

        calculator.getSunInfo();
        calculator.getSunInfo().getSunrise();
        calculator.getSunInfo().getSunrise().toString();
    }

    public void setMinutesFrequency(int in){
        if (in >= 0 && in < 60) {
            minutesFrequency = in;
        }
    }

    public boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo(); //jakiś problem z tą linijką kodu (klasa Connectivity Manager jest cała w errorach)
        if(activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            return true;
        }
        else return false;
    }

    private void initializeWeatherData()
    {
        if (isOnline(this))
        {
            initializeOnlineWeather();
        }
        else
        {
            noInternetConnectionInfo();
            initializeOfflineWeather();
        }
        initializeOfflineWeather();
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    private void initializeOnlineWeather()
    {
        FetchWeatherData fetchWeatherData = new FetchWeatherData();
        DefaultValue defaultValue = new DefaultValue();
        fetchWeatherData.execute(defaultValue.system + "", defaultValue.selectedLocation.getCity().toString(), defaultValue.selectedLocation.getCountry().toString());
        try
        {
            DefaultValue.weather = fetchWeatherData.get();
            saveWeather(DefaultValue.weather);
            saveLocations(DefaultValue.locations);
        }
        catch (ExecutionException | InterruptedException e)
        {
            e.printStackTrace();
        }
        Toast.makeText(this, R.string.updateData, Toast.LENGTH_SHORT).show();

        saveWeather(DefaultValue.weather);
        saveLocations(DefaultValue.locations);
    }

    private void saveWeather(Weather weather)
    {
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(getCacheDir() + File.separator + R.string.weatherFile);

            if (file.exists())
            {
                file.delete();
            }

            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(weather);
            objectOutputStream.close();

        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void saveLocations(KnownLocations knownLocations)
    {
        FileOutputStream fileOutputStream = null;
        try
        {
            File file = new File(getCacheDir() + File.separator + getResources().getString(R.string.weatherFile));    //źle

            if (file.exists())
            {
                file.delete();
            }

            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(knownLocations);
            objectOutputStream.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void noInternetConnectionInfo()
    {
        new AlertDialog.Builder(this)
                .setTitle("Tryb Offline")
                .setMessage("Brak połączenia z Internetem!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), R.string.noWeatherInfo, Toast.LENGTH_LONG).show();
                    }
                })
                .show();
    }

    private void initializeOfflineWeather()
    {
        DefaultValue.locations = loadLocations();
        DefaultValue.weather = loadWeather();
    }

    private KnownLocations loadLocations()
    {
        FileInputStream fileInputStream = null;
        KnownLocations knownLocations = new KnownLocations();
        try
        {
            File file = new File(getCacheDir() + File.separator + R.string.locationsFile);
            if (file.exists())
            {
                fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                knownLocations = (KnownLocations) objectInputStream.readObject();
                objectInputStream.close();
            }
        }
        catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
        if (knownLocations.getLocationList().size() <= 0)
        {
            knownLocations = new KnownLocations();
            knownLocations.addLocation(new Location("lodz", "pl"));
        }
        return knownLocations;
    }

    private Weather loadWeather()
    {
        FileInputStream fileInputStream = null;
        Weather weather = null;
        try
        {
            File file = new File(getCacheDir() + File.separator + R.string.weatherFile);

            if (file.exists())
            {
                fileInputStream = new FileInputStream(file);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                weather = (Weather) objectInputStream.readObject();
                objectInputStream.close();
            }

        }
        catch (ClassNotFoundException | IOException e)
        {
            e.printStackTrace();
        }
        return weather;
    }
}
