package com.bawei.minestudy.activity;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;

import com.bawei.minestudy.R;
import com.bawei.minestudy.entity.VideoEntity;
import com.bawei.mybase.view.BaseActivity;

import java.io.Serializable;

import master.flame.danmaku.controller.DrawHandler;
import master.flame.danmaku.danmaku.model.BaseDanmaku;
import master.flame.danmaku.danmaku.model.DanmakuTimer;
import master.flame.danmaku.danmaku.model.IDanmakus;
import master.flame.danmaku.danmaku.model.android.DanmakuContext;
import master.flame.danmaku.danmaku.model.android.Danmakus;
import master.flame.danmaku.danmaku.parser.BaseDanmakuParser;
import master.flame.danmaku.ui.widget.DanmakuView;

/**
 * MineStudy
 * name: DetailsActivity
 * time: 2021/8/21 9:14.
 * author: 王益德
 * Describe:
 */
public class DetailsActivity extends BaseActivity {

    private com.bawei.minestudy.myView.MyVideo detailsRetBtn;
    private android.widget.EditText detailsFlyText;
    private android.widget.Button detailsFlyBtn;
    private DanmakuView detailsFlyTextView;
    private DanmakuContext danmakuContext;

    @Override
    public void initView() {
        detailsRetBtn = findViewById(R.id.details_ret_btn);
        detailsRetBtn.getTitleTextView().setVisibility(View.VISIBLE);
        detailsRetBtn.getBackButton().setVisibility(View.VISIBLE);
        detailsFlyText = findViewById(R.id.details_fly_text);
        detailsFlyBtn = findViewById(R.id.details_fly_btn);
        detailsFlyTextView = findViewById(R.id.details_fly_text_view);
        danmakuContext = DanmakuContext.create();
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        VideoEntity.DataBean video = (VideoEntity.DataBean) intent.getSerializableExtra("video");
        detailsRetBtn.setUp(video.getVideopath(),true,video.getAuthname());
        detailsRetBtn.startPlayLogic();
        detailsRetBtn.setShowFullAnimation(true);

        //视频视图获取返回按钮 并设置监听
        detailsRetBtn.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取放大按钮 并设置
        detailsRetBtn.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                detailsRetBtn.startWindowFullscreen(DetailsActivity.this,false,false);
            }
        });

        //弹幕视图设置 准备
        detailsFlyTextView.prepare(new BaseDanmakuParser() {
            @Override
            protected IDanmakus parse() {
                return new Danmakus();
            }
        },danmakuContext);


        //详情页弹幕视图 设置回调
        detailsFlyTextView.setCallback(new DrawHandler.Callback() {
            @Override
            public void prepared() {
                detailsFlyTextView.start();
            }

            @Override
            public void updateTimer(DanmakuTimer timer) {

            }

            @Override
            public void danmakuShown(BaseDanmaku danmaku) {

            }

            @Override
            public void drawingFinished() {

            }
        });

        detailsFlyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BaseDanmaku danmaku = danmakuContext.mDanmakuFactory.createDanmaku(BaseDanmaku.TYPE_SCROLL_RL);
                danmaku.text = detailsFlyText.getText().toString();
                danmaku.padding = 5;
                danmaku.priority = 1;//设置优先级
                danmaku.isLive = true;//是否一直存在
                danmaku.textSize = 32f; //设置字体大小
                danmaku.setTime(detailsFlyTextView.getCurrentTime()+1200);  //发送弹幕时间 这里设置（当前视频播放时间+1200毫秒）
                danmaku.textColor = Color.GREEN;    //字体颜色
                danmaku.textShadowColor = Color.BLACK;//阴影
                danmaku.underlineColor = Color.BLACK;   //下划线
                danmaku.borderColor = Color.GREEN;  //边框

                detailsFlyTextView.addDanmaku(danmaku); //添加弹幕
            }
        });


    }

    @Override
    public int bindLayout() {
        return R.layout.activity_details;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        detailsRetBtn.getGSYVideoManager().stop();
        detailsRetBtn.release();
    }


}
