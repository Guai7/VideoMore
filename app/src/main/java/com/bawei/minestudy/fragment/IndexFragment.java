package com.bawei.minestudy.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bawei.minestudy.R;
import com.bawei.minestudy.activity.LogAndRegActivity;
import com.bawei.minestudy.adapter.FragmentAdapter;
import com.bawei.minestudy.fragment.indexFragment.FaFragment;
import com.bawei.minestudy.fragment.indexFragment.GuanFragment;
import com.bawei.minestudy.fragment.indexFragment.JingFragment;
import com.bawei.minestudy.utils.SpUtils;
import com.bawei.mybase.view.BaseFragment;
import com.flyco.tablayout.SlidingTabLayout;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

/**
 * MineStudy
 * name: IndexFragment
 * time: 2021/8/19 14:02.
 * author: 王益德
 * Describe:
 */
public class IndexFragment extends BaseFragment implements View.OnClickListener{
    private ImageView menuBtn;
    private TextView loginBtn;
    private SlidingTabLayout indexTabs;
    private ViewPager indexVp;

    @Override
    public void initView() {

        menuBtn = (ImageView) findViewById(R.id.menu_btn);
        loginBtn = (TextView) findViewById(R.id.login_btn);
        indexTabs = (SlidingTabLayout) findViewById(R.id.index_tabs);
        indexVp = (ViewPager) findViewById(R.id.index_vp);


        //获取Sp对象 获取登录布尔值 判断是否已登录 登录就显示Menu否则显示登录text
//        SharedPreferences login = SpUtils.getInstance().getLogin();
//        boolean login1 = login.getBoolean("login", false);
//        if (login1) {
            loginBtn.setVisibility(View.GONE);
            menuBtn.setVisibility(View.VISIBLE);
//        } else {
//            loginBtn.setVisibility(View.VISIBLE);
//            menuBtn.setVisibility(View.GONE);
//        }


    }

    @Override
    public void initData() {
        menuBtn.setOnClickListener(this);
        loginBtn.setOnClickListener(this);

        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new GuanFragment());
        fragments.add(new FaFragment());
        fragments.add(new JingFragment());
        List<String> titles = new ArrayList<>();
        titles.add("关注");
        titles.add("发现");
        titles.add("精选");

        FragmentAdapter fragmentAdapter = new FragmentAdapter(getActivity().getSupportFragmentManager(),fragments,titles);
        indexVp.setAdapter(fragmentAdapter);

//        indexTabs.setScrollBarStyle(View.SCROLLBARS_INSIDE_INSET);

        indexTabs.setViewPager(indexVp);
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_index;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //menu按钮发送广播 通过activity打开slidingMenu
            case R.id.menu_btn:
                EventBus.getDefault().postSticky("menu");
                break;
            //登录按钮跳转登陆页面
            case R.id.login_btn:
                Intent intent = new Intent(getActivity(), LogAndRegActivity.class);
                startActivity(intent);
                break;
        }
    }

}
