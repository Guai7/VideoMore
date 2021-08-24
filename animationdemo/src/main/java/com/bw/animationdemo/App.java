package com.bw.animationdemo;

import android.app.Application;
import android.content.Context;

/**
 * MineStudy
 * name: App
 * time: 2021/8/24 16:57.
 * author: 王益德
 * Describe:
 */
public class App extends Application {
    public static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
}
