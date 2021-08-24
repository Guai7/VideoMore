package com.bawei.minestudy;

import android.app.Application;
import android.content.Context;

/**
 * MineStudy
 * name: App
 * time: 2021/8/19 16:32.
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
