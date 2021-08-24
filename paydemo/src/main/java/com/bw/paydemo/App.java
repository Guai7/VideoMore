package com.bw.paydemo;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;

/**
 * MineStudy
 * name: App
 * time: 2021/8/24 8:27.
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
