package com.bawei.minestudy.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.alipay.sdk.app.AuthTask;
import com.alipay.sdk.app.EnvUtils;
import com.alipay.sdk.app.PayTask;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bawei.minestudy.R;
import com.bawei.minestudy.adapter.FragmentAdapter;
import com.bawei.minestudy.fragment.CityFragment;
import com.bawei.minestudy.fragment.IndexFragment;
import com.bawei.minestudy.fragment.MineFragment;
import com.bawei.minestudy.fragment.MsgFragment;
import com.bawei.minestudy.pay.AuthResult;
import com.bawei.minestudy.pay.H5PayDemoActivity;
import com.bawei.minestudy.pay.PayResult;
import com.bawei.minestudy.pay.util.OrderInfoUtil2_0;
import com.bawei.minestudy.utils.SpUtils;
import com.bawei.minestudy.utils.WindowUtils;
import com.bawei.mybase.view.BaseActivity;
import com.blankj.utilcode.util.SizeUtils;
import com.frame.lib_common.slidingmenu.SlidingMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * MineStudy
 * name: MainActivity
 * time: 2021/8/19 1:37.
 * author: 王益德
 * Describe:
 */
public class MainActivity extends BaseActivity {

    private androidx.viewpager.widget.ViewPager mainVp;
    private BottomNavigationBar mainBottom;
    private SlidingMenu menu;

    @Override
    public void initView() {

        EventBus.getDefault().register(this);
        //设置导航栏颜色
        WindowUtils.setStatusBarColor(this,R.color.white);
        //设置导航栏字体颜色
        WindowUtils.setAndroidNativeLightStatusBar(this,true);

        mainVp = findViewById(R.id.main_vp);
        mainBottom = findViewById(R.id.main_bottom);
        if (SpUtils.getInstance().getLogin().getBoolean("login",false)){
            initMenu();
        }
    }

    @Override
    public void initData() {

        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);

        //设置适配器数据源
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new CityFragment());
        fragments.add(new MsgFragment());
        fragments.add(new MineFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);

        //设置适配器
        mainVp.setAdapter(fragmentAdapter);

        //（下）导航栏
        mainBottom.addItem(new BottomNavigationItem(R.drawable.ic_launcher_background,"首页"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background,"同城"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background,"录视频"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background,"消息"))
                .addItem(new BottomNavigationItem(R.drawable.ic_launcher_background,"我"))
                .setMode(BottomNavigationBar.MODE_FIXED)
                .initialise();

        mainBottom.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                mainVp.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

        mainVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainBottom.selectTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void open(String menu){
        if (menu.equals("menu")&& SpUtils.getInstance().getLogin().getBoolean("login",false)){
            this.menu.toggle();
        }
    }

    private void initMenu() {
        this.menu = new SlidingMenu(this);
        this.menu.setBehindWidth(SizeUtils.dp2px(320));
        this.menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        this.menu.setMenu(R.layout.sliding_layout);
        this.menu.setMode(SlidingMenu.LEFT);

        this.menu.findViewById(R.id.slide_pay_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payV2();
            }
        });

        this.menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        this.menu.showMenu();
        this.menu.showContent();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 用于支付宝支付业务的入参 app_id。
     */
    public static final String APPID = "2021000118607477";

    /**
     * 用于支付宝账户登录授权业务的入参 pid。
     */
    public static final String PID = "";

    /**
     * 用于支付宝账户登录授权业务的入参 target_id。
     */
    public static final String TARGET_ID = "";

    /**
     *  pkcs8 格式的商户私钥。
     *
     * 	如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个，如果两个都设置了，本 Demo 将优先
     * 	使用 RSA2_PRIVATE。RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议商户使用
     * 	RSA2_PRIVATE。
     *
     * 	建议使用支付宝提供的公私钥生成工具生成和获取 RSA2_PRIVATE。
     * 	工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1
     */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDAfzVSn57Pcg3RQ6bbV0UiXISlqEHHqpsApcWYR484xiMOSaeIUY5Hi3Qgj/8AhxZdNXuZYBU3zvRWbNWm0ILpTYx3it6TMrfCUNGdVRsNri7vTvbxXVknzcWp7if2JGavuzCt7HiqTH1oFYvxOR1+z2D/w+aOIt6BlJZZQWATXi+dd74pcaTN/c0vYCxFldXdZnMoYUcyrHgvXI3UYoGCL8/RIvCGvJZhrd/0kP+SPCCnx9IGlYJsMYQeX9e0xSX/XuqY2n/HdsGfk233dWreHTx0b0qYXAYers88QjKGWxAhJ5089UyQ+OTaxeFCchTkyvvoioHYpTZoMzv8PQH9AgMBAAECggEAMICc7emcOUbSpnS2WTP/Dm5jWd4wXN/m2F3skLcPJk3+TT5PwTBTekFwDDhcF0TyZJ/yOa4VCo/RzujzWGnXYnOQvho2hL5VwNdeSZdFDshhuRibbiuIKFhXwr5oQUPgv8yZsmkP6lEFlDFBppEulmIoXal9sTil+d53l5u6WoaD1H4216OuklpW/oesXKrV3jUktDUAm7zdpoUIzOClHp4fvtRXcASMeNnPU+fCXr0scXIet/iRZ37VZ2FX7HU/OPUQp3FwhfLjRIFAdvWQvzKKwI5mmlQMOBZfup5Qk2tYxQcuBuUblWUgPtBLB44hA+mt26FQlld4Pgy+BjtkfQKBgQDpZ3lkmEbp02u1am1YIdf+W2EBucuh+7uLxQJHTx4yLuuWwJYLbGt3SMBTJ4jkFuEVio6/v9C4lzZj97tprDHvFcoqPBn33BYqlNCjnl+04sZdjW16ReXFPMjHJWAD3oaIwQcbbRW63IcdYCAZDgLRBUQcECdWdm39HlGehNYsXwKBgQDTIeqj89HJaVctImfI1StsfY5lvMFCC93ZT1IF3+vNu21M0OzKvgM6JNB2trDKYNZR6A38dl+wHJHgsbErJDLaazJYRIj4C9GOenOWHYWliW9N2vl0iI6dln+jNj7LNVeabDxNkOH+5lZgJyfqh9p9d0Y7D0vNnJlRO4qUmWCvIwKBgAine4rNUqPM4QuC/Lbt2lJmFPy5ZOGGsfeGwITgXCBEORwH3AhkZr+vhOIQbzT13DauQoi9lgGiKbQfNWW/qsJQroROYjAciv3dMrc+YhIU7oH9gnesK87aaB9qNVsvXxGnBppvuwSItMHUFJyZNmWFqnbX6n18OqvN+fzEtCRFAoGASOATGoZFKi03DvgqNJMHywnHhexSFJb9+006tZZl0PxZrMMMiKpveLlOTzud6CzmrRzY/wRo4OProlATJB35g5SuqvL1CBEkQvaXQQBtqga3KXtk0Ul1WIWjBtXpdMCzvTSQ2AEjUJV2yPea+oPr8ZSi5C3Sb2UXMofHvKT4T1sCgYEAo4O9oU2jY06DR/aLQy1vodHCAFrb3swOFRmFh9QUu3Lbix1AdSjQZ2ZEqetk1LCFtO2KNlEtgZ+ytmfvU6zITP4mvn/Aey6It85e+6ZlnXAZAH+V76ZY7RXtIJ4LctVHr1RKW7yw1KIdidp4F//gB0GLR7ZZ6sreRg4Gy4wR/Cs=";
    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();

                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(MainActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
            }
        };
    };
    public void payV2() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            Toast.makeText(MainActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
            return;
        }

        /*
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo 的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        final Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(MainActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


}
