package com.irvingryan.okhttpdemo;

import android.app.Application;

/**
 * Created by wentao on 2016/7/19.
 */
public class MyApplication extends Application {
    private static MyApplication instance;
    @Override
    public void onCreate() {
        super.onCreate();
        instance=this;
    }

    public static MyApplication getInstance() {
        return instance;
    }
}
