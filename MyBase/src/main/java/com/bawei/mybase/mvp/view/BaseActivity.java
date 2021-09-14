package com.bawei.mybase.mvp.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bawei.mybase.mvp.presenter.IPresenter;

import javax.inject.Inject;

public abstract
class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity,IView{

    @Inject
    protected P presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(bindLayout());

        initView();
        initData();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (presenter != null) {
            presenter.destroy();
            presenter = null;
        }
    }
}
