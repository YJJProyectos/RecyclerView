package com.android.jyang.recyclerview.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.android.jyang.recyclerview.Utils.Util;

public class SplashActivity extends AppCompatActivity {

    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        prefs = getSharedPreferences("Datos", Context.MODE_PRIVATE);
        Intent intentLogin = new Intent(this, LoginActivity.class );
        Intent intentMain = new Intent(this, MainActivity.class);

        if ( !mailVacio(prefs) && !pwdVacio(prefs) ) {
            startActivity(intentMain);
        } else {
            startActivity(intentLogin);
        }
        // matar la instancia de esta activity para no poder volver
        finish();
    }

    private boolean mailVacio(SharedPreferences prefs) {
        return TextUtils.isEmpty(Util.getEmailPrefs(prefs));
    }
    private boolean pwdVacio(SharedPreferences prefs) {
        return TextUtils.isEmpty(Util.getPwdPrefs(prefs));
    }
}
