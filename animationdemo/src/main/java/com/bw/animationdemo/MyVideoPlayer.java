package com.bw.animationdemo;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.ViewTarget;
import com.bw.animationdemo.db.DaoSession;
import com.bw.animationdemo.db.UserMoneyDao;
import com.bw.animationdemo.entity.UserMoney;
import com.bw.animationdemo.utils.MoneyManager;
import com.bw.paydemo.PayMainActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.umeng.socialize.ShareAction;

import org.greenrobot.eventbus.EventBus;
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
    private View inflate1 = LayoutInflater.from(getContext()).inflate(R.layout.gift_popuwindow,null);
    private PopupWindow window;
    private List<GiftEntity.DataBean> nowGiftList;
    private RecyclerAdapter recyclerAdapter;
    private DaoSession daoSession;
    private UserMoney money;
    private View inflate;

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
            if (window!=null){
                window.dismiss();
            }

            if (time == 0L) {
                time = System.currentTimeMillis();
            } else {
                if (System.currentTimeMillis() - time < 400) {
                    ImageView imageView = new ImageView(getContext());

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
    protected void touchDoubleUp(MotionEvent e) {

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


//            6130849204a0b741437c586f
            EventBus.getDefault().postSticky("share");

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

            if (money==null) {
                daoSession = MoneyManager.getInstance(getContext()).getDaoSession();
                List<UserMoney> userMonies = daoSession.loadAll(UserMoney.class);
                int position = userMonies.size();

                money = daoSession.queryBuilder(UserMoney.class).where(UserMoneyDao.Properties.Id.eq(position)).build().unique();

                TextView moneyText = inflate.findViewById(R.id.this_money);
                moneyText.setText(""+money.getMoney());

            }


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

        window.setOutsideTouchable(true);
        window.setTouchable(true);

        inflate = LayoutInflater.from(getContext()).inflate(R.layout.gift_popuwindow, null);
        window.setContentView(inflate);
    }

    /**
     * 适配器点击事件
     * @param adapter
     * @param view
     * @param position
     */
    @Override
    public void onItemClick(@NonNull @NotNull BaseQuickAdapter<?, ?> adapter, @NonNull @NotNull View view, int position) {



        ImageView imgView = findViewById(R.id.gift_view);
        imgView.setVisibility(VISIBLE);

        String gifImg = nowGiftList.get(position).getGifImg();
        Glide.with(getContext()).load(gifImg).into(imgView);
        
        if (money.getMoney()<nowGiftList.get(position).getPrice()){
            AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            dialog.setTitle("余额不足是否充值？");
            dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });
        }

        //更新数据库数据
        money.setMoney(money.getMoney()-nowGiftList.get(position).getPrice());
        daoSession.update(money);

        //更新视图数据
        TextView moneyText = inflate.findViewById(R.id.this_money);
        moneyText.setText(""+money.getMoney());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                imgView.setVisibility(INVISIBLE);
            }
        },3000);
    }
}
