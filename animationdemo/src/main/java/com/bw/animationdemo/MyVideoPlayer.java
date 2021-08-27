package com.bw.animationdemo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.ViewTarget;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * MineStudy
 * name: MyVideoPlayer
 * time: 2021/8/26 16:46.
 * author: 王益德
 * Describe:
 */
public class MyVideoPlayer extends StandardGSYVideoPlayer implements View.OnClickListener, OnItemClickListener {

    private Long time = 0L;
    private boolean rota = true;
    private ImageView playerHeartFalse;
    private ImageView playerHeartTrue;
    private ImageView playerChat;
    private ImageView playerShare;
    private ImageView playerGift;
    private View inflate1 = LayoutInflater.from(App.context).inflate(R.layout.gift_popuwindow,null);
    private PopupWindow window;
    private List<GiftEntity.DataBean> nowGiftList;
    private RecyclerAdapter recyclerAdapter;

    public MyVideoPlayer(Context context) {
        super(context);
        initView();
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (time == 0L) {
                time = System.currentTimeMillis();
            } else {
                if (System.currentTimeMillis() - time < 400) {
                    ImageView imageView = new ImageView(App.context);

                    imageView.setImageDrawable(getResources().getDrawable(R.mipmap.xin2));
                    imageView.setLayoutParams(new ViewGroup.LayoutParams(100, 100));

                    imageView.setX(event.getX());
                    imageView.setY(event.getY());

                    //设置旋转
                    if (rota) {
                        imageView.setRotation((float) (Math.random() * 20));
                        rota = false;
                    } else {
                        imageView.setRotation((float) (340 + Math.random() * 20));
                        rota = true;
                    }


                    ObjectAnimator alpha = ObjectAnimator.ofFloat(imageView, "alpha", 1, 0);
                    ObjectAnimator translationY = ObjectAnimator.ofFloat(imageView, "translationY", event.getY(), -1);

                    alpha.setDuration(500);
                    translationY.setDuration(1500);

                    addView(imageView);

                    alpha.start();
                    translationY.start();

                    playerHeartFalse.setVisibility(View.INVISIBLE);
                    playerHeartTrue.setVisibility(View.VISIBLE);
                } else {
                    time = System.currentTimeMillis();
                }
            }
        }

        return super.onTouch(v, event);

    }


    @Override
    public int getLayoutId() {
        return R.layout.player_view;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int id = v.getId();
        //点赞
        if (id == R.id.player_heart_false) {

            playerHeartFalse.setVisibility(View.INVISIBLE);
            playerHeartTrue.setVisibility(View.VISIBLE);
        //取消点赞
        } else if (id == R.id.player_heart_true) {

            playerHeartFalse.setVisibility(View.VISIBLE);
            playerHeartTrue.setVisibility(View.INVISIBLE);
        //打开评论
        } else if (id == R.id.player_chat) {


        //打开分享
        } else if (id == R.id.player_share) {


        //点击礼物按钮时
        } else if (id == R.id.player_gift) {
            //显示window
            window.showAtLocation(playerGift, Gravity.BOTTOM,0,0);

            //请求数据
            OkGo.<String>get("http://www.fenghongzhang.com:7777/gift/getAll").execute(new StringCallback() {
                @Override
                public void onSuccess(Response<String> response) {
                    String body = response.body();

                    Gson gson = new Gson();
                    GiftEntity giftEntity = gson.fromJson(body, GiftEntity.class);

                    nowGiftList = giftEntity.getData();
                    initGiftAdapter();

                }
            });
        }
    }

    /**
     * 初始化礼物适配器
     */
    private void initGiftAdapter() {
        if (recyclerAdapter==null){
            recyclerAdapter = new RecyclerAdapter(nowGiftList);

            View contentView = window.getContentView();
            RecyclerView rv = contentView.findViewById(R.id.gift_rv);

            rv.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.VERTICAL));
            rv.setAdapter(recyclerAdapter);

            recyclerAdapter.setOnItemClickListener(this);

            recyclerAdapter.notifyDataSetChanged();

            nowGiftList = recyclerAdapter.getData();
        }
    }

    /**
     * 初始化控件和popupWindow
     */
    private void initView() {
        playerHeartFalse = findViewById(R.id.player_heart_false);
        playerHeartFalse.setOnClickListener(this);

        playerHeartTrue = findViewById(R.id.player_heart_true);
        playerHeartTrue.setOnClickListener(this);

        playerChat = findViewById(R.id.player_chat);
        playerChat.setOnClickListener(this);

        playerShare = findViewById(R.id.player_share);
        playerShare.setOnClickListener(this);

        playerGift = findViewById(R.id.player_gift);
        playerGift.setOnClickListener(this);

        window = new PopupWindow();
        window.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        window.setHeight(500);

        window.setAnimationStyle(Animation.ZORDER_BOTTOM);

        window.setOutsideTouchable(true);

        View inflate = LayoutInflater.from(App.context).inflate(R.layout.gift_popuwindow, null);
        window.setContentView(inflate);
    }

    private static View thatView = LayoutInflater.from(App.context).inflate(R.layout.player_view,null);

    /**
     * 适配器点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {
        ImageView showView = thatView.findViewById(R.id.gift_show_img);
        showView.setVisibility(VISIBLE);
        RequestBuilder<Drawable> load = Glide.with(App.context).load(nowGiftList.get(position).getGifImg());
        hand(load,showView);

        /**
         * 延迟关闭
         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                showView.setVisibility(INVISIBLE);
            }
        },3000);
    }

    /**
     * 确保加载到屏幕
     * @param load
     * @param showView
     */
    private void hand(RequestBuilder<Drawable> load, ImageView showView) {

        if (load!=null){
            load.into(showView);
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    hand(load,showView);
                }
            },1000);
        }
    }
}
