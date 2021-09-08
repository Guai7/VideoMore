package com.bawei.minestudy;

import android.app.Application;
import android.content.Context;

import com.alibaba.android.arouter.launcher.ARouter;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

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
        ARouter.init(this);
        umInit();
        qqInit();
    }

    private void qqInit() {
        // QQ设置
        PlatformConfig.setQQZone("101830139","5d63ae8858f1caab67715ccd6c18d7a5");
        PlatformConfig.setQQFileProvider("com.tencent.sample2.fileprovider");
    }

    private void umInit() {
        UMConfigure.init(this,"6130849204a0b741437c586f","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");
        UMConfigure.setLogEnabled(true);
    }
}
