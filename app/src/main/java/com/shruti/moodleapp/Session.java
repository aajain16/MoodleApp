package com.shruti.moodleapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by OM on 6/28/2017.
 */

public class Session {

    private SharedPreferences prefs;

    public Session(Context context)
    {
        prefs= PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void setid(String username) {
        prefs.edit().putString("username",username).commit();
    }

    public String getid() {

        String username=prefs.getString("username","");
        return username;
    }
}
