package com.example.vishwanath.Utility;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by android-linux-mv on 8/11/17.
 */
public class Utility
{
    public static boolean isNetworkAvailable(Context context)
    {
        try {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }
}