package com.bawei.minestudy.activity;

import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.bawei.minestudy.R;
import com.bawei.minestudy.adapter.FragmentAdapter;
import com.bawei.minestudy.fragment.CityFragment;
import com.bawei.minestudy.fragment.IndexFragment;
import com.bawei.minestudy.fragment.MineFragment;
import com.bawei.minestudy.fragment.MsgFragment;
import com.bawei.minestudy.utils.SpUtils;
import com.bawei.minestudy.utils.WindowUtils;
import com.bawei.mybase.view.BaseActivity;
import com.blankj.utilcode.util.SizeUtils;
//import com.bw.animationdemo.myview.AnimationMainActivity;
//import com.bw.paydemo.PayMainActivity;
import com.bw.animationdemo.myview.AnimationMainActivity;
import com.bw.paydemo.PayMainActivity;
import com.frame.lib_common.slidingmenu.SlidingMenu;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * MineStudy
 * name: MainActivity
 * time: 2021/8/19 1:37.
 * author: 王益德
 * Describe:
 */

/**
 * TODO: 有注释的东西 记得取消掉
 *
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
//        if (SpUtils.getInstance().getLogin().getBoolean("login",false)){
            initMenu();
//        }
    }

    @Override
    public void initData() {

        //设置适配器数据源
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new IndexFragment());
        fragments.add(new CityFragment());
        fragments.add(new MsgFragment());
        fragments.add(new MineFragment());
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

    //Fragment点击menu而接受的广播
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void open(String menu){

        if (menu.equals("menu")){
            this.menu.toggle();
        }
    }

    //初始化SlidingMenu
    private void initMenu() {
        this.menu = new SlidingMenu(this);
        this.menu.setBehindWidth(SizeUtils.dp2px(320));
        this.menu.attachToActivity(this,SlidingMenu.SLIDING_CONTENT);
        this.menu.setMenu(R.layout.sliding_layout);
        this.menu.setMode(SlidingMenu.LEFT);

        this.menu.findViewById(R.id.slide_pay_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PayMainActivity.class);
                startActivity(intent);
            }
        });

        this.menu.findViewById(R.id.slide_animation_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnimationMainActivity.class);
                startActivity(intent);
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


}
