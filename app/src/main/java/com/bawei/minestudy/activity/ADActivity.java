package com.bawei.minestudy.activity;

import android.content.Intent;
import android.os.Handler;
import android.view.animation.AlphaAnimation;

import com.bawei.minestudy.R;
import com.bawei.mybase.view.BaseActivity;

public class ADActivity extends BaseActivity  {

    private Handler handler;
    private android.widget.ImageView adImg;

    @Override
    public void initView() {
        handler = new Handler();
        adImg = findViewById(R.id.ad_img);
    }

    @Override
    public void initData() {

        //透明度动画（感觉不好看）
//        AlphaAnimation animation = new AlphaAnimation(0,1);
//        animation.setDuration(3000);
//        adImg.startAnimation(animation);

        //延时三秒进入主页面
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(ADActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_ad;
    }
}