package com.bawei.minestudy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.bawei.minestudy.App;

/**
 *MineStudy
 *name: SpUtils
 *time: 2021/8/19 16:30.
 *author: 王益德
 *Describe:
 */
public class SpUtils {
    private static SpUtils utils;
    private SharedPreferences sp;



    private SpUtils() {
        sp = App.context.getSharedPreferences("login", Context.MODE_PRIVATE);
    }

    static enum SingletonEnum{
        INSTANCE;
        private SpUtils utils;

        private SingletonEnum() {
            utils = new SpUtils();
        }

        public SpUtils getInstance(){
            return utils;
        }
    }

    public static SpUtils getInstance(){
        return SingletonEnum.INSTANCE.getInstance();
    }

    public SharedPreferences getLogin() {
        return sp;
    }
}
