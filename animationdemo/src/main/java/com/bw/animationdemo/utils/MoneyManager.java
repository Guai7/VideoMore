package com.bw.animationdemo.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.bw.animationdemo.db.DaoMaster;
import com.bw.animationdemo.db.DaoSession;


/**
 * MineStudy
 * name: MoneyManager
 * time: 2021/9/3 9:25.
 * author: 王益德
 * Describe:
 */
public class MoneyManager{
    private DaoSession daoSession;
    private static MoneyManager manager;

    private MoneyManager(Context context) {
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(context,"money.db");

        SQLiteDatabase db = devOpenHelper.getWritableDatabase();

        DaoMaster daoMaster = new DaoMaster(db);

        daoSession = daoMaster.newSession();
    }

    public synchronized static MoneyManager getInstance(Context context) {
        if (manager == null) {
            synchronized (MoneyManager.class){
                if (manager == null) {
                    manager = new MoneyManager(context);
                }
            }
        }
        return manager;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
