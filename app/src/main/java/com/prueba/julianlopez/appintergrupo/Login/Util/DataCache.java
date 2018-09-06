package com.prueba.julianlopez.appintergrupo.Login.Util;

import android.content.Context;
import com.github.rtoshiro.secure.SecureSharedPreferences;


public  class DataCache {



    public static void writeData(Context context,String key,String value){

        SecureSharedPreferences settings = new SecureSharedPreferences(context);
        SecureSharedPreferences.Editor editor = settings.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static String readData(Context context,String key){

        SecureSharedPreferences settings = new SecureSharedPreferences(context);
        return settings.getString(key, "");
    }



}
