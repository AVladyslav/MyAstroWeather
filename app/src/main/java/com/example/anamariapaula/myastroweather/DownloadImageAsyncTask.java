package com.example.anamariapaula.myastroweather;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;


public class DownloadImageAsyncTask extends AsyncTask<Void, Void, Drawable> {
    View view;
    String code;
    public DownloadImageAsyncTask(String code, View view) {
        this.code = code;
        this.view = view;
    }
    @Override
    protected Drawable doInBackground(Void... params) {
        InputStream is = null;
        try {
            is = (InputStream) new URL("http://l.yimg.com/a/i/us/we/52/"+code+".gif").getContent();
            Drawable d = Drawable.createFromStream(is, "src name");
            return d;
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        super.onPostExecute(drawable);
        ImageView iv = (ImageView)view.findViewById(R.id.imageViewWeather);
        iv.setImageDrawable(drawable);

    }
}

