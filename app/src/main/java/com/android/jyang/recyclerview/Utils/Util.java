package com.android.jyang.recyclerview.Utils;

import android.content.SharedPreferences;

/**
 * Created by jyang on 9/4/2018.
 */

public class Util {

    public static String getEmailPrefs(SharedPreferences prefs ) {
        return prefs.getString("mail", "");
    }

    public static String getPwdPrefs(SharedPreferences prefs) {
        return prefs.getString("pwd", "");
    }
}
