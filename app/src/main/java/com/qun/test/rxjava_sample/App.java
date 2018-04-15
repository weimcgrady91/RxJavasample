package com.qun.test.rxjava_sample;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by Administrator on 2018/4/13 0013.
 */

public class App extends Application {
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        Log.e("weiqun12345","Application Thread id=" + Thread.currentThread().getId());
    }
}
