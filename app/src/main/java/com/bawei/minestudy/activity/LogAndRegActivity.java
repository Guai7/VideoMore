package com.bawei.minestudy.activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bawei.minestudy.R;
import com.bawei.minestudy.adapter.FragmentAdapter;
import com.bawei.minestudy.fragment.LoginFragment;
import com.bawei.minestudy.fragment.RegisterFragment;
import com.bawei.minestudy.utils.WindowUtils;
import com.bawei.mybase.mvp.view.BaseActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * MineStudy
 * name: LogAndRegActivity
 * time: 2021/8/19 14:31.
 * author: 王益德
 * Describe:
 */
public class LogAndRegActivity extends BaseActivity{
    private androidx.viewpager.widget.ViewPager loginRegisterVp;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void initView() {
        WindowUtils.setStatusBarColor(this, R.color.white);
        WindowUtils.setAndroidNativeLightStatusBar(this,true);

        loginRegisterVp = findViewById(R.id.login_register_vp);
    }

    @Override
    public void initData() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new LoginFragment());
        fragments.add(new RegisterFragment());
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),fragments);

        loginRegisterVp.setAdapter(fragmentAdapter);

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_log_reg;
    }

    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void getMsg(String msg){
        if (msg.equals("goReg")){
            loginRegisterVp.setCurrentItem(1);
        }else {
            loginRegisterVp.setCurrentItem(0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
