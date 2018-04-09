package com.android.jyang.recyclerview.App;

import android.app.Application;
import android.os.SystemClock;

import java.time.Clock;

/**
 * Created by jyang on 9/4/2018.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}
