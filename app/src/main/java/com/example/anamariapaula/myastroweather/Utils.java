package com.example.anamariapaula.myastroweather;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class Utils {
    public static boolean isOnline(Context context)
    {
        boolean isOnline;
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isOnline = networkInfo != null && networkInfo.isConnectedOrConnecting();
        if (!isOnline) {
            noInternetConnectionInfo(context);
        }
        return isOnline;
    }

    private static void noInternetConnectionInfo(final Context context)
    {
        new AlertDialog.Builder(context)
                .setTitle("Offline")
                .setMessage("No internet connection!")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }
}
