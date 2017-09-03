package com.example.anamariapaula.myastroweather;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class SaveDataAsyncTask extends AsyncTask<Void, Void, List<String>> {
    AstroWeather mainActivity;

    public SaveDataAsyncTask(AstroWeather mainActivity) {
        this.mainActivity = mainActivity;
    }

    @Override
    protected List<String> doInBackground(Void... params) {
        SharedPreferences sharedpreferences = mainActivity.getSharedPreferences("ShPe", Context.MODE_PRIVATE);
        String lokalizacja = sharedpreferences.getString("lokalizacja","lodz");
        String units = sharedpreferences.getString("jednostki","c");
        ItemMethods itemMethods = new ItemMethods();
        String json = itemMethods.fetchItems(lokalizacja, mainActivity, units);

        try{
            //zapis do pliku
            FileOutputStream outputStream;
            outputStream = mainActivity.openFileOutput(lokalizacja, Context.MODE_PRIVATE);
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (IOException e){}
        return null;
    }

    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
        if(mainActivity.pager != null){
            int x = mainActivity.pager.getCurrentItem();
            mainActivity.pager.setAdapter(mainActivity.adapter);
            mainActivity.pager.setCurrentItem(x);
        }
    }
}
